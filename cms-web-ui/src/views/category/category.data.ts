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
  // {
  //   title: '所属上级类型',
  //   dataIndex: 'parentName',
  //   width: 100,
  //   align: 'left',
  // },
  // {
  //   title: '排序',
  //   dataIndex: 'sort',
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
];