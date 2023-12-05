import { Upload } from '/@/api/sys/file';
import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { h } from 'vue';
import { Tag } from 'ant-design-vue';
export const columns: BasicColumn[] = [
  {
    title: '评论人名称',
    dataIndex: 'commentator',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '评论人邮箱',
    dataIndex: 'email',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '评论内容',
    dataIndex: 'content',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '是否审核',
    dataIndex: 'status',
    width: 60,
    customRender: ({ record }) => {
      const status = record.status;
      const enable = ~~status === 1;
      const color = enable ? 'green' : 'red';
      const text = enable ? '审核通过' : '未审核';
      return h(Tag, { color: color }, () => text);
    },
  },
  {
    title: '类型',
    dataIndex: 'type',
    width: 60,
    customRender: ({ record }) => {
      const type = record.type;
      let text = '-';
      switch (type) {
        case 1:
          text = '评论'
          break;
        case 1:
          text = '留言'
          break;
      
        default:
          break;
      }
      return h(Tag, {}, () => text);
    },
  },
  {
    title: '创建时间',
    dataIndex: 'createdAt',
    width: 100,
    align: 'left',
    sorter: false
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'commentator',
    label: '评论人名称',
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
    field: 'status',
    defaultValue:1,
    label: '是否审核',
    component: 'Switch',
    componentProps:{
      checkedChildren: '是',
      checkedValue: 1,
      unCheckedValue:0,
      unCheckedChildren: '否'
    },
    colProps: {
      span: 24,
    },
  },
  // {
  //   field: 'commentator',
  //   label: '评论人名称',
  //   required: false,
  //   component: 'Input',
  //   colProps: {
  //     span: 12,
  //   },
  // },
  // {
  //   field: 'content',
  //   label: '评论内容',
  //   required: false,
  //   component: 'Input',
  //   colProps: {
  //     span: 12,
  //   },
  // },
  // {
  //   field: 'createdAt',
  //   label: '创建时间',
  //   required: false,
  //   component: 'Input',
  //   colProps: {
  //     span: 12,
  //   },
  // },
];