<template>
  <PageWrapper dense contentFullHeight fixedHeight class="m-4">
    <Card class="m-4">
      <BasicForm @register="registerForm" class="w-1/2"/>
      <!-- <BasicTable @register="registerTable">
        <template #toolbar>
          <a-button type="primary" @click="handleCreate"> 新增 </a-button>
        </template>
        <template #action="{ record }">
          <TableAction
            :actions="[
              {
                icon: 'clarity:note-edit-line',
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: 'ant-design:delete-outlined',
                color: 'error',
                popConfirm: {
                  title: '是否确认删除',
                  confirm: handleDelete.bind(null, record),
                },
              },
            ]"
          />
        </template>
      </BasicTable> -->
      <configModal @register="registerModal" @success="handleSuccess" />
    </Card>
  </PageWrapper >
</template>
<script lang="ts">
  import { PageWrapper } from '/@/components/Page';
  import { defineComponent ,onMounted} from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { columns, searchFormSchema } from './config.data';
  import configModal from './configModal.vue';
  import { getconfigListByPage,UpdateBatch, getconfigList,getInfo,deleteByIds } from '/@/api/config/config.ts';
import { useRoute } from 'vue-router'
import { Card } from 'ant-design-vue'
  import { BasicForm, Rule, useForm } from '/@/components/Form';
  export default defineComponent({
    name: 'config',
    components: { BasicTable, TableAction, PageWrapper,Card ,configModal,BasicForm },
    setup() {
      const [registerForm, { resetFields, resetSchema, setFieldsValue, validate }] = useForm({
        labelWidth: 150,
        schemas: [],
        showActionButtonGroup: true,
        showResetButton: false,
        submitButtonOptions: {
          text:'确认修改'
        },
        actionColOptions: {
          span:24
        },
        submitFunc:(async ()=> {
          const data = await validate();
          await UpdateBatch(data);
        })
      });
      const route = useRoute()
      const [registerModal, { openModal,setModalProps }] = useModal();
      const [registerTable, { reload ,setProps,getForm,setLoading}] = useTable({
        title: '列表',
        api: getconfigListByPage,
        columns,
        formConfig: {
          labelWidth: 120,
          schemas: searchFormSchema,
          showAdvancedButton: false,
          showResetButton: true,
          autoSubmitOnEnter: true,
        },
        canColDrag: true,
        useSearchForm: true,
        bordered: true,
        showIndexColumn: false,
        actionColumn: {
          width: 100,
          title: '操作',
          dataIndex: 'action',
          slots: { customRender: 'action' },
          fixed: 'right',
        },
      });

      function handleCreate() {
        let list = route.path.split('/')
        openModal(true, {
          isUpdate: false,
          record: {
            type:list[list.length-1]
          }
        });
      }

      async function handleEdit(record: Recordable) {
        setLoading(true)
        const data = await getInfo(record.id)
        setLoading(false)
        setModalProps({title: '修改'});
        openModal(true, {
          record:data,
          isUpdate: true,
        });
      }

      function handleDelete(record: Recordable) {
        deleteByIds({ids:record.id}).then(() => {
          reload();
        });
      }

      function handleSuccess() {
        reload();
      }
      onMounted(() => {
        let list = route.path.split('/')
        getconfigList({ type: list[list.length - 1] }).then(res => {
          let formConfig = {
            type:list[list.length - 1]
          }
          let formSchema = []
          res.map(item => {
            formConfig[item.code] = item.value
            formSchema.push({
              field: item.code,
              label: item.name,
              required: false,
              component: 'Input',
              defaultValue:item.value,
              colProps: {
                span: 24,
              },
            })
          })
          setFieldsValue(formConfig)
          resetSchema(formSchema)
          // if()
          // if
          // 
          // setFieldsValue({

          // })
        })
        // setProps({
        //   searchInfo: {
        //     type:list[list.length-1]
        //   }
        // })
      })

      return {
        registerForm,
        registerTable,
        registerModal,
        handleCreate,
        handleEdit,
        handleDelete,
        handleSuccess,
      };
    },
  });
</script>