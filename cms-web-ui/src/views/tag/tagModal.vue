<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>
<script lang="ts">
  import { defineComponent, ref, computed, unref } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, Rule, useForm } from '/@/components/Form';
  import { formSchema } from './tag.data';
  import { Insert,Update } from '/@/api/tag/tag.ts';
  import { CheckExistParams } from "/@/api/model/baseModel";

  export default defineComponent({
    name: 'tagModal',
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
        setModalProps({ confirmLoading: false});
        isUpdate.value = !!data?.isUpdate;
        let formData = data.record;
        if (unref(isUpdate)) {
          setFieldsValue({
            ...formData,
          });
        }
      });

      const getTitle = computed(() => (!unref(isUpdate) ? '新增' : '修改'));

      async function handleSubmit() {
        try {
          const values = await validate();
          setModalProps({ confirmLoading: true });
          let api = unref(isUpdate) ? Update : Insert
          await api(values);
          closeModal();
          emit('success');
        } finally {
          setModalProps({ confirmLoading: false ,width:600});
        }
      }

      return { registerModal, registerForm, getTitle, handleSubmit };
    },
  });
</script>