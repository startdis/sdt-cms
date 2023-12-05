import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { h } from 'vue';
import { Tag } from 'ant-design-vue';
export const columns: BasicColumn[] = [
  // {
  //   dataIndex: 'code',
  //   title: '编码',
  //   width: 100,
  //   align: 'left',
  //   fixed:'left'
  // },
  {
    title: '名称',
    dataIndex: 'name',
    width: 100,
    align: 'left',
    fixed:'left'
  },
  {
    title: '别名',
    dataIndex: 'alias',
    width: 100,
    align: 'left',
    fixed:'left'
  },
  {
    title: '是否启用',
    dataIndex: 'status',
    width: 60,
  },
  {
    title: '排序',
    dataIndex: 'sort',
    width: 100,
    align: 'left',
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
  }
  // {
  //   title: '所属上级类型',
  //   dataIndex: 'parentName',
  //   width: 100,
  //   align: 'left',
  // },
];

export const searchFormSchema: FormSchema[] = [
  
  {
    field: 'name',
    label: '名称',
    required: false,
    component: 'Input',
    colProps: { span: 6 },
    
  },
  // {
  //   field: 'code',
  //   label: '编码',
  //   required: false,
  //   component: 'Input',
  //   colProps: { span: 6 },
    
  // }
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
    label: '名称',
    required: true,
    component: 'Input',
    show: true,
    rules: [{
        required: true,
        whitespace: true,
        message: '名称不能为空！',
      },
      {
        max: 32,
        message: '字符长度不能大于32！',
      }],
  },
  {
    field: 'alias',
    label: '别名',
    required: false,
    component: 'Input',
    show: true,
    rules: [
      {
        max: 32,
        message: '字符长度不能大于32！',
      }],
  },
  // {
  //   field: 'code',
  //   label: '编码',
  //   required: true,
  //   component: 'Input',
  //   show: true,
  // },
  {
    field: 'parentId',
    label: '所属上级分类',
    component: 'TreeSelect',
    componentProps: {
      treeNodeFilterProp: 'name',
      replaceFields: {
        title: 'name',
        key: 'id',
        value: 'id',
      },
      getPopupContainer: () => document.body,
    },
    required: false,
    colProps: { span: 24 },
  },
  {
    field: 'sort',
    label: '排序',
    required: false,
    component: 'InputNumber',
    show: true,
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