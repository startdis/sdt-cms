import { Upload } from '/@/api/sys/file';
import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { h } from 'vue';
import { Tag } from 'ant-design-vue';
export const columns: BasicColumn[] = [
  {
    title: '链接类型',
    dataIndex: 'type',
    width: 100,
    align: 'left',
    sorter: false,
    customRender: ({ record }) => { 
      let str = ''
      const columnExpandList = {
        1: {
          label: '友链',
          value: '1',
        },
        2: {
          label: '推荐网站',
          value: '2',
        },
        3: {
          label: '个人网站',
          value: '3',
        },
      }
      
      str = columnExpandList[record['type']]?.label
      console.log("record['type']",record['type'],str);
      return h('div', {}, str);
    },
  },
  {
    title: '网站名称',
    dataIndex: 'name',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '网站链接',
    dataIndex: 'url',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '网站排序',
    dataIndex: 'sort',
    width: 100,
    align: 'left',
    sorter: false
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'name',
    label: '网站名称',
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
    label: '链接类型',
    required: true,
    component: 'Select',
    componentProps: {
      options: [
        {
          label:'友链',
          value:1,
        },
        {
          label:'推荐网站',
          value:2,
        },
        {
          label:'个人网站',
          value:3,
        },
      ]
    },
    colProps: {
      span: 24,
    },
  },
  {
    field: 'name',
    label: '网站名称',
    required: true,
    component: 'Input',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'url',
    label: '网站链接',
    required: true,
    component: 'Input',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'description',
    label: '网站描述',
    required: false,
    component: 'InputTextArea',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'sort',
    label: '网站排序',
    required: true,
    component: 'InputNumber',
    colProps: {
      span: 12,
    },
  },
];