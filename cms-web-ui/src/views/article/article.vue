<template>
  <PageWrapper dense contentFullHeight fixedHeight>
    <BasicTable @register="registerTable">
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
    </BasicTable>
    <articleModal @register="registerModal" @success="handleSuccess" />
  </PageWrapper >
</template>
<script lang="ts">
  import { PageWrapper } from '/@/components/Page';
  import { defineComponent } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { columns, searchFormSchema } from './article.data';
  import articleModal from './articleModal.vue';
  import { useRoute,useRouter } from 'vue-router'
  import { getarticleListByPage, getInfo,deleteByIds } from '/@/api/article/article.ts';
  export default defineComponent({
    name: 'article',
    components: { BasicTable, TableAction, PageWrapper ,articleModal },
    setup() {
      const router = useRouter()
      const [registerModal, { openModal,setModalProps }] = useModal();
      const [registerTable, { reload ,setLoading}] = useTable({
        title: '列表',
        api: getarticleListByPage,
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
        router.push('/addArticle')
        return
        openModal(true, {
          isUpdate: false,
        });
      }

      async function handleEdit(record: Recordable) {
        router.push(`/addArticle?id=${record.id}`)
        return
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

      return {
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