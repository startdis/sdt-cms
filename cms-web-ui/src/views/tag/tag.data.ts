import { Upload } from '/@/api/sys/file';
import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { h } from 'vue';
import { Tag } from 'ant-design-vue';
export const columns: BasicColumn[] = [
  {
    title: '标签名称',
    dataIndex: 'name',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '是否启用',
    dataIndex: 'status',
    width: 60,
    customRender: ({ record }) => {
      const status = record.status;
      const enable = ~~status === 1;
      const color = enable ? 'green' : 'red';
      const text = enable ? '启用' : '停用';
      return h(Tag, { color: color }, () => text);
    },
  },
  {
    title: '创建时间',
    dataIndex: 'createdAt',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '更新时间',
    dataIndex: 'updatedAt',
    width: 100,
    align: 'left',
    sorter: false
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'name',
    label: '标签名称',
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
    field: 'name',
    label: '标签名称',
    required: true,
    component: 'Input',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'status',
    defaultValue:1,
    label: '是否启用',
    component: 'Switch',
    componentProps:{
      checkedChildren: '启用',
      checkedValue: 1,
      unCheckedValue:0,
      unCheckedChildren: '禁用'
    },
    colProps: {
      span: 24,
    },
  },
];