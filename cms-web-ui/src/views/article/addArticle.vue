<template>
  <PageWrapper dense contentFullHeight fixedHeight class="m-4">
    <div class="p-4 bg-white scrollbar__wrap">
      <BasicForm @register="registerForm">
        <template #SetTitle="{ model, field }">
          <div class="flex">
            <div style="flex:1" class="pr-4">
              <Input v-model:value="model[field]" allowClear :maxlength="128" placeholder="请输入文章标题"></Input>  
            </div>
            <div>
              <Button type="primary" shape="round" ghost class="mr-4" @click="submitContent(0)">保存草稿</Button>
              <Button type="primary" shape="round"  @click="submitContent(1)">发布文章</Button>
            </div>
          </div>
        </template>
      </BasicForm>
    </div>
    <articleModal @register="registerModal" @success="handleSuccess" />
    <!-- <div class="flex p-4 w-full justify-center bg-white bottom-0 border border-l-0 border-r-0 border-b-0 border-primary">
      
    </div> -->
  </PageWrapper>
</template>
<script lang="ts">
  import { defineComponent, ref, computed, unref, onMounted } from 'vue';
  import { BasicModal, useModal } from '/@/components/Modal';
  import { BasicForm, Rule, useForm } from '/@/components/Form';
  import { formSchema,formSchemaEditor,formSchemaMd } from './article.data';
  import { Insert,Update,getInfo } from '/@/api/article/article.ts';
  import { PageWrapper } from '/@/components/Page';
  import { Button,Input,Tag,Popover } from 'ant-design-vue'
  import articleModal from './articleModal.vue';
  import { useRoute,useRouter } from 'vue-router'
  import { useMessage } from '/@/hooks/web/useMessage';
  import { useTabs } from '/@/hooks/web/useTabs';
  export default defineComponent({
    // beforeRouteEnter(to, from, next) {
    //   console.log('toto',to);
    //   next((vm => {
    //     vm.initPage()
    //   }));
    // },
    name: 'articleModals',
    components: { BasicModal,PageWrapper, BasicForm,Button,Input,Tag,Popover,articleModal},
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const { close } = useTabs();
      const { createMessage } = useMessage()
      const isUpdate = ref(false);
      const formData = ref({});
      const route = useRoute()
      const router = useRouter()
      const {currentRoute} = useRouter();
      const [registerModal, { openModal }] = useModal();
      const [registerForm, { resetFields,resetSchema,updateSchema,setFieldsValue,getFieldsValue, validate }] = useForm({
        labelWidth: 100,
        schemas: [],
        showActionButtonGroup: false,
      });

      async function initPage() {
        await resetFields();
        resetSchema([
          ...formSchema,
          ...formSchemaEditor
        ])
        isUpdate.value = route.query.id ? true : false;
        if (unref(isUpdate)) {
          const res = await getInfo(route.query.id)
          formData.value = res
          setFieldsValue({
            ...res
          });
        }
        await updateSchema([
          {
            field: 'content',
            componentProps: {
              options: {
                changComponentType: (componentType) => {
                  if (componentType == 'MD') {
                    resetSchema([
                      ...formSchema,
                      ...formSchemaEditor
                    ])
                  } else {
                    console.log(componentType);
                    resetSchema([
                      ...formSchema,
                      ...formSchemaMd
                    ])
                  }
                }
              }
            },
          },
        ]);
      }

      onMounted(() => {
        initPage()
      })

      async function submitContent(status) {
        try {
          const values = await validate();
          // 保存操作直接 可以直接修改和新增数据 不需要弹框填写更多信息
          if (!status) {
            let params = {
              ...formData.value,
              ...values,
              status
            }
            let api = unref(isUpdate) ? Update : Insert;
            api(params)
              .then(() => {
              // router.push(`/addArticle?id=${res.articleId}`)
                createMessage.success('保存草稿成功')
                close(unref(currentRoute))
            })
            .catch(() => {
            });
            return
          }
          setFieldsValue({
            status
          })
          let record = {
            ...formData.value,
            ...values,
            status
          }
          openModal(true, {
            isUpdate: false,
            record
          });
        } catch (error) {
          if (!getFieldsValue().content) {
            createMessage.warning('文章内容不能为空！')
            return
          }
        }
      }
      async function handleSuccess(articleId) {
        close(unref(currentRoute))
        return
        router.push(`/article?id=${articleId}`)
      }

      return { registerModal,registerForm,initPage, submitContent,handleSuccess};
    },
  });
</script>