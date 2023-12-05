<template>
  <PageWrapper dense contentFullHeight fixedHeight>
    <BasicTable @register="registerTable">
      <template #toolbar>
        <a-button type="primary" @click="handleCreate"> 新增 </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'status'">
            <Switch :checked="record['status']" checkedChildren='启用' unCheckedChildren='停用' :checkedValue='1' :unCheckedValue='0' @click="changeStatus(record)"></Switch>
          </template>
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
    <linkModal @register="registerModal" @success="handleSuccess" />
  </PageWrapper >
</template>
<script lang="ts">
  import { PageWrapper } from '/@/components/Page';
  import { defineComponent } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { columns, searchFormSchema } from './link.data';
  import linkModal from './linkModal.vue';
  import { getlinkListByPage, getInfo,Update,deleteByIds } from '/@/api/link/link.ts';
  import { useMessage } from '/@/hooks/web/useMessage';
const {createWarningModal } = useMessage();
import { Switch } from 'ant-design-vue';
  export default defineComponent({
    name: 'link',
    components: { BasicTable, TableAction, PageWrapper ,Switch,linkModal },
    setup() {
      const [registerModal, { openModal,setModalProps }] = useModal();
      const [registerTable, { reload ,setLoading}] = useTable({
        title: '列表',
        api: getlinkListByPage,
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
        openModal(true, {
          isUpdate: false,
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
      function changeStatus(record) {
        if (record.status) {
          createWarningModal({
            title: '提示',
            content: '确认停用？',
            onOk: () => {
              setLoading(true)
              Update({
                id: record.id,
                status:'0'
              }).then(() => {
                setLoading(false)
                reload()
              }).catch(() => {
                setLoading(false)
              })
            }
          })
        } else {
          setLoading(true)
          Update({
            id: record.id,
            status:'1'
          }).then(() => {
            setLoading(false)
            reload()
          }).catch(() => {
            setLoading(false)
          })
        }
      }

      return {
        changeStatus,
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