<template>
  <div>
    <BasicTable @register="registerTable">
      <template #toolbar>
        <a-button type="primary" @click="handleCreate"> 新增 </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'status'">
          <Switch :checked="record['status']" checkedChildren='启用' unCheckedChildren='停用' :checkedValue='1' :unCheckedValue='0' @click="changeStatus(record)"></Switch>
        </template>
        <template v-if="column.key === 'action'">
          <TableAction
            :actions="[
              {
                tooltip: '添加子分类',
                icon: 'ant-design:plus-outlined',
                onClick: handleCreateChild.bind(null, record),
              },
              {
                tooltip: '修改',
                icon: 'clarity:note-edit-line',
                onClick: handleEdit.bind(null, record),
              },
              {
                tooltip: '删除',
                icon: 'ant-design:delete-outlined',
                color: 'error',
                onClick: (e)=>{e.stopPropagation();},
                popConfirm: {
                  title: '是否确认删除',
                  confirm: handleDelete.bind(null, record),
                  placement: 'left'
                },
              },
            ]"
          />
        </template>
      </template>
    </BasicTable>
    <CategoryModal @register="registerModal" @success="handleSuccess" />
  </div>
</template>
<script lang="ts">
  import { defineComponent,onMounted,ref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { columns, searchFormSchema } from './category.data';
  import CategoryModal from './categoryModal.vue';
  import { getTree, getInfo,Update, deleteByIds } from '/@/api/category/category';
  import { useMessage } from '/@/hooks/web/useMessage';
const { createMessage,createWarningModal } = useMessage();
import { Switch } from 'ant-design-vue';
import {  useRoute } from 'vue-router';
  export default defineComponent({
    name: 'Typeconfig',
    components: { BasicTable, TableAction, CategoryModal,Switch },
    setup() {
      const route = useRoute()
      let type = ref(0)
      const [registerModal, { openModal,setModalProps }] = useModal();
      const [registerTable, { reload ,setProps,setLoading}] = useTable({
        title: '列表',
        api: getTree,
        columns,
        formConfig: {
          labelWidth: 120,
          schemas: searchFormSchema,
          showAdvancedButton: false,
          showResetButton: false,
          autoSubmitOnEnter: true,
        },
        searchInfo: {
          type:0
        },
        pagination:false,
        canColDrag: true,
        useSearchForm: true,
        bordered: true,
        showIndexColumn: false,
        actionColumn: {
          width: 100,
          title: '操作',
          dataIndex: 'action',
          fixed: 'right',
        },
      });
      onMounted(() => {
      })
      function handleCreate() {
        setModalProps({title: '新增分类'});
        openModal(true, {
          isUpdate: false,
        });
      }

      async function handleEdit(record: Recordable, e) {
        setLoading(true)
        const data: TypeconfigInfo = await getInfo(record.id)
        setLoading(false)
        e.stopPropagation();
        setModalProps({title: '修改分类'});
        openModal(true, {
          record: {
            ...data,
          },
          isUpdate: true,
        });
      }

      function handleCreateChild(record: Recordable, e) {
        e.stopPropagation();
        setModalProps({title: '新增【'+record.name+'】的子分类'});
        record = {
          parentId: record.id,
          status: 1,
          type:type.value
        };
        openModal(true, {
          record,
          isUpdate: false,
        });
      }

      function handleDelete(record: Recordable) {
        if(record.children&&record.children.length>0){
          createMessage.warning("有子分类，不能删除！")
          return;
        }
        deleteByIds({ids:record.id}).then((res) => {
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
            closable: true,
            okCancel:true,
            onOk: () => {
              setLoading(true)
              Update({
                id: record.id,
                status:0
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
            status:1
          }).then(() => {
            setLoading(false)
            reload()
          }).catch(() => {
            setLoading(false)
          })
        }
      }

      return {
        type,
        changeStatus,
        handleCreateChild,
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