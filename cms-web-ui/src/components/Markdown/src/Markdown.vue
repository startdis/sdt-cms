<template>
  <div ref="wrapRef"></div>
</template>
<script lang="ts">
  import type { Ref } from 'vue';
  import {
    defineComponent,
    ref,
    unref,
    nextTick,
    computed,
    watch,
    onBeforeUnmount,
    onDeactivated,
  } from 'vue';
  import Vditor from 'vditor';
  import 'vditor/dist/index.css';
  import { useLocale } from '/@/locales/useLocale';
import { useModalContext } from '../../Modal';
  import { toolbar } from './toolbar'
  import { useRootSetting } from '/@/hooks/setting/useRootSetting';
import { onMountedOrActivated } from '/@/hooks/core/onMountedOrActivated';
import { getAppEnvConfig } from '/@/utils/env';
import { GetUploadLink,Upload } from '/@/api/sys/file'
const {
  VITE_GLOB_UPLOAD_URL,
  } = getAppEnvConfig();
  import { getTheme } from './getTheme';

  type Lang = 'zh_CN' | 'en_US' | 'ja_JP' | 'ko_KR' | undefined;

  export default defineComponent({
    inheritAttrs: false,
    props: {
      height: { type: Number, default: 360 },
      value: { type: String, default: '' },
    },
    emits: ['change', 'get', 'update:value'],
    setup(props, { attrs, emit }) {
      const wrapRef = ref<ElRef>(null);
      const vditorRef = ref(null) as Ref<Nullable<Vditor>>;
      const initedRef = ref(false);

      const modalFn = useModalContext();

      const { getLocale } = useLocale();
      const { getDarkMode } = useRootSetting();
      const valueRef = ref(props.value || '');

      watch(
        [() => getDarkMode.value, () => initedRef.value],
        ([val, inited]) => {
          if (!inited) {
            return;
          }
          instance
            .getVditor()
            ?.setTheme(getTheme(val) as any, getTheme(val, 'content'), getTheme(val, 'code'));
        },
        {
          immediate: true,
          flush: 'post',
        },
      );

      watch(
        () => props.value,
        (v) => {
          if (v !== valueRef.value) {
            instance.getVditor()?.setValue(v);
          }
          valueRef.value = v;
        },
      );

      const getCurrentLang = computed((): 'zh_CN' | 'en_US' | 'ja_JP' | 'ko_KR' => {
        let lang: Lang;
        switch (unref(getLocale)) {
          case 'en':
            lang = 'en_US';
            break;
          case 'ja':
            lang = 'ja_JP';
            break;
          case 'ko':
            lang = 'ko_KR';
            break;
          default:
            lang = 'zh_CN';
        }
        return lang;
      });
      function init() {
        const wrapEl = unref(wrapRef) as HTMLElement;
        if (!wrapEl) return;
        const bindValue = { ...attrs, ...props };
        const insEditor = new Vditor(wrapEl, {
          // 设置外观主题
          theme: getTheme(getDarkMode.value) as any,
          lang: unref(getCurrentLang),
          mode: 'sv',
          fullscreen: {
            index: 520,
          },
          outline: {
            enable: true,
            position:'right'
          },
          upload: {
            accept: 'image/*',
            // url: VITE_GLOB_UPLOAD_URL,
            // linkToImgUrl: VITE_GLOB_UPLOAD_URL,
            headers: {
              'X-SERVICE-TYPE': 'webService',
              'X-GROUP-TENANT-ID':'1667075454562709506',
              'X-COMPANY-TENANT-ID': '1667075454562709506',
            },
            fieldName: 'multipartFile',
            handler:  (files) => {
              const array = []
              files.forEach(file => {
                array.push(Upload({ file:file }))
              })
              Promise.all(array).then((list) => { 
                console.log('listlist',list);
                list.forEach(async (item) => {
                  let res = await GetUploadLink({ expires: 30000, objectName: item.data.data.fileUrl })
                  insEditor?.insertValue(`![${item.data.data.fileName}](${res})<br/>`); // 文本编辑器中插入图片
                })
              })
            },
          },
          toolbar,
          preview: {
            theme: {
              // 设置内容主题
              current: getTheme(getDarkMode.value, 'content'),
            },
            hljs: {
              // 设置代码块主题
              style: getTheme(getDarkMode.value, 'code'),
            },
            actions: [],
          },
          input: (v) => {
            valueRef.value = v;
            emit('update:value', v);
            emit('change', v);
          },
          after: () => {
            nextTick(() => {
              modalFn?.redoModalHeight?.();
              insEditor.setValue(valueRef.value);
              vditorRef.value = insEditor;
              initedRef.value = true;
              emit('get', instance);
            });
          },
          blur: () => {
            //unref(vditorRef)?.setValue(props.value);
          },
          ...bindValue,
          cache: {
            enable: false,
          },
        });
      }

      const instance = {
        getVditor: (): Vditor => vditorRef.value!,
      };

      function destroy() {
        const vditorInstance = unref(vditorRef);
        if (!vditorInstance) return;
        try {
          vditorInstance?.destroy?.();
        } catch (error) {}
        vditorRef.value = null;
        initedRef.value = false;
      }

      onMountedOrActivated(init);

      onBeforeUnmount(destroy);
      onDeactivated(destroy);
      return {
        wrapRef,
        ...instance,
      };
    },
  });
</script>
<style lang="less">
  .vditor-toolbar {
    .vditor-toolbar__item {
      margin:0 .3rem !important;
    }
  }
</style>