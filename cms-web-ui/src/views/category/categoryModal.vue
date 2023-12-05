<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>
<script lang="ts">
  import { defineComponent, ref, computed, unref } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, Rule, useForm } from '/@/components/Form';
  import { formSchema } from './category.data';
  import { Insert,Update, getTree,getInfo} from '/@/api/category/category.ts';

  export default defineComponent({
    name: 'TypeconfigModal',
    components: { BasicModal, BasicForm },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const isUpdate = ref(true);

      const [registerForm, { resetFields, updateSchema, setFieldsValue, validate }] = useForm({
        labelWidth: 100,
        schemas: formSchema,
        showActionButtonGroup: false,
      });

      const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
        await resetFields();
        setModalProps({ confirmLoading: false });
        isUpdate.value = !!data?.isUpdate;
        let formData = data.record;
        console.log(formData);
        
        const treeData = await getTree();
        await updateSchema([
          {
            field: 'parentId',
            componentProps: {
              treeData,
            },
          }
        ]);
        
        
        if (unref(isUpdate)) {
          setFieldsValue({
            ...formData,
            parentId:formData.parentId === '0' ? null : formData.parentId
          });
        } else {
          setFieldsValue({
            ...formData
          });
        }
      });

      const getTitle = computed(() => (!unref(isUpdate) ? '新增' : '修改'));

      async function handleSubmit() {
        try {
          const values = await validate();
          setModalProps({ confirmLoading: true });
          if (values.parentId) {
            const data = await getInfo(values.parentId);
            values.parentName = data.name
          } else {
            values.parentId = '0'
          }
          let api = unref(isUpdate) ? Update : Insert
          await api(values);
          closeModal();
          emit('success');
        } finally {
          setModalProps({ confirmLoading: false });
        }
      }

      return { registerModal, registerForm, getTitle, handleSubmit };
    },
  });
</script>