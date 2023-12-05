<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <BasicForm @register="registerForm">
      <template #SetTags="{ model, field }">
        <Popover trigger="click" placement="bottomLeft">
          <template #title>
            <div class="flex justify-between">
              <span>标签</span>
            </div>
          </template>
          <template #content>
            <div v-loading="tagListLoading">
              <Input
                style="width: 400px"
                v-model:value="tagValue"
                placeholder="请输入文字搜索，Enter键入可添加自定义标签"
                @pressEnter="pressEnter"
                @change="tagValueChange"
              >
                <!-- <template #addonAfter>
                  <SearchOutlined class="ant-select-suffix" @click="getTagsList(tagValue)" />
                </template> -->
              </Input>
              <!-- 所有的库中的标签 -->
              <p class="m-4" style="width: 400px; height: 200px; overflow-y: auto">
                <Tag
                  class="cursor-pointer mb"
                  style="margin-bottom: 0.5rem"
                  v-for="item in allTagList"
                  :key="item.id"
                  :color="getColor(item)"
                  @click="handleAddTag(item)"
                  >{{ item.name }}</Tag
                >
              </p>
            </div>
          </template>
          <div>
            <!-- 选择的标签 -->
            <template v-if="selectTagList && selectTagList.length">
              <Tag
                class="cursor-pointer"
                style="margin-bottom: 0.5rem"
                v-for="(item,index) in selectTagList"
                :key="item.id"
                closable
                @close="closeTags(index)"
                >{{ item.name }}</Tag
              >
            </template>
            <p class="py-2" style="width: 400px">
              <Tag class="cursor-pointer" style="background: #fff; border-style: dashed">
                <PlusOutlined />
                添加标签
              </Tag>
            </p>
          </div>
        </Popover>
      </template>
    </BasicForm>
    <template #footer>
      <div>
        <Button class="mr-4" shape="round" @click="cancel">取消</Button>
        <Button type="primary" shape="round" ghost class="mr-4" @click="handleSubmit(0)">保存草稿</Button>
        <Button type="primary" shape="round"  @click="handleSubmit(1)">发布文章</Button>
      </div>
    </template>
  </BasicModal>
</template>
<script lang="ts">
  import { defineComponent, ref, computed, unref } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, useForm } from '/@/components/Form';
  import { formSchemaEnd } from './article.data';
  import { Insert, Update } from '/@/api/article/article';
  import { articleInfo } from '/@/api/article/model/articleModel';
  import { gettagList,Insert as TagInsert} from '/@/api/tag/tag';
  import { PlusOutlined, CloseOutlined, SearchOutlined } from '@ant-design/icons-vue';
import { Select, Input, Tag, Popover,Button, InputSearch } from 'ant-design-vue';
import { useMessage } from '/@/hooks/web/useMessage';
export default defineComponent({
  name: 'articleModal',
  components: {
    BasicModal,
    BasicForm,
      Tag,
      Popover,
      Select,
      InputSearch,
    Input,
    Button,
      PlusOutlined,
      CloseOutlined,
      SearchOutlined,
    },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const { createMessage } = useMessage()
      const isUpdate = ref(true);
      const tagListLoading = ref(true);
      const tagValue = ref('');
      let allTagList:any = ref([]);
      let selectTagList:any = ref([]);
      let formData = ref(<articleInfo>{});
      const [
        registerForm,
        { resetFields, updateSchema, clearValidate, setFieldsValue, validate },
      ] = useForm({
        labelWidth: 100,
        schemas: formSchemaEnd,
        showActionButtonGroup: false,
      });

      const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
        await resetFields();
        setModalProps({ confirmLoading: false, width: 600 });
        isUpdate.value = !!data?.isUpdate;
        formData.value = data.record;
        let { content, articleTags, ...reset } = formData.value;
        selectTagList.value = articleTags && articleTags.length ? articleTags : [];
        setFieldsValue({
          ...reset,
          coverImage: formData.coverImageList?.length ? formData.coverImageList : [],
        });
        getTagsList();
        await updateSchema([
          {
            field: 'categoryId',
            componentProps: {
              onChange: (e: { label: string; value:string}) => {
                clearValidate('categoryId');
                setFieldsValue({
                  categoryName: e.label,
                  categoryId: e.value,
                });
              },
            },
          },
        ]);
      });
      async function getTagsList() {
        tagListLoading.value = true
        allTagList.value = await gettagList({ status:1 });
        tagListLoading.value = false
      }

      const getTitle = computed(() => '发布文章');

      async function handleSubmit(status) {
        const values = await validate();
        let api = unref(isUpdate) ? Update : Insert;
        let articleTags:any = [];
        selectTagList.value.map((item) => {
          articleTags.push({
            tagId: item.id,
          });
        });
        setModalProps({ loading: true });
        api({
          ...values,
          content: formData.value.content,
          articleTags,
          status,
          coverImage:
            Array.isArray(values.coverImage) == true
              ? values.coverImage
                  .map((item) => {
                    return item.id;
                  })
                  .join(',')
              : values.coverImage,
        })
          .then((res) => {
            setModalProps({ loading: false });
            closeModal();
            if (status) {
              createMessage.success('发布成功')
            } else {
              createMessage.success('保存草稿成功')
            }
            emit('success');
          })
          .catch(() => {
            setModalProps({ loading: false });
          });
      }
      // 已经选择的标签颜色为置灰
      function getColor(row) {
        let articleTags = selectTagList.value && selectTagList.value.length ? selectTagList.value : [];
        let flag = articleTags.find((item) => {
          return item.id == row.id;
        });
        if (flag) {
          return 'default'
        } else {
          return 'processing'
        }
      }
      // tag输入框值变化请求tag列表
      async function tagValueChange() {
        tagListLoading.value = true
        let params = { status: 1 }
        if (tagValue.value) {
          params.name = tagValue.value
        }
        let res: any = await gettagList(params);
        tagListLoading.value = false
        allTagList.value = res
      }
      // tags回车键事件 请求接口查询是否已经存在 否则就调用新增接口去新增获取到tagId
      async function pressEnter(e) {
        tagListLoading.value = true
        let params = { status: 1 }
        if (e.target._value) {
          params.name = e.target._value
        }
        let res: any = await gettagList(params);
        tagListLoading.value = false
        if (res && res.length) {
          allTagList.value = res
        } else {
          tagListLoading.value = true
          TagInsert({ name: e.target._value,status:1 }).then((res) => {
            tagListLoading.value = false
            selectTagList.value.push({
              id: res.tagId,
              name:res.tagName
            })
            tagValue.value = ''
            tagValueChange()
          })
        }
      }
      // 点击tag标签事件
      function handleAddTag(row) {
        let articleTags = selectTagList.value && selectTagList.value.length ? selectTagList.value : [];

        let flag = articleTags.find((item) => {
          return item.id == row.id;
        });
        if (!flag) {
          selectTagList.value.push(row);
        }
      }
      // 删除tag事件
      function closeTags(index) {
        selectTagList.value.splice(index,1)
      }
      function cancel() {
        closeModal();
      }
      return {
        registerModal,
        registerForm,
        getTitle,
        tagListLoading,
        allTagList,
        tagValue,
        handleSubmit,
        closeTags,
        getTagsList,
        cancel,
        selectTagList,
        pressEnter,
        gettagList,
        getColor,
        handleAddTag,
        tagValueChange
      };
    },
  });
</script>
