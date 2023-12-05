import { Upload } from '/@/api/sys/file';
import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { h } from 'vue';
import { Tag } from 'ant-design-vue';
export const columns: BasicColumn[] = [
  {
    title: '配置项编码',
    dataIndex: 'code',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '配置项名称',
    dataIndex: 'name',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '配置项的值',
    dataIndex: 'value',
    width: 100,
    align: 'left',
    sorter: false
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'code',
    label: '配置项编码',
    component: 'Input',
    colProps: {
      span: 6,
    },
  },
  {
    field: 'name',
    label: '配置项名称',
    component: 'Input',
    colProps: {
      span: 6,
    },
  },
];
export const formSchema: FormSchema[] = [
  {
    field: 'id',
    label: '',
    required: false,
    show:false,
    component: 'Input',
    colProps: {
      span: 12,
    },
  },
  {
    field: 'type',
    label: '',
    required: true,
    show:false,
    component: 'Input',
    colProps: {
      span: 12,
    },
  },
  {
    field: 'code',
    label: '配置项编码',
    required: true,
    component: 'Input',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'name',
    label: '配置项名称',
    required: true,
    component: 'Input',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'value',
    label: '配置项的值',
    required: false,
    component: 'Input',
    colProps: {
      span: 24,
    },
  },
];