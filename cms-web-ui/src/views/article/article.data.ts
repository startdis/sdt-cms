import { Upload } from '/@/api/sys/file';
import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { getTree } from '/@/api/category/category.ts';
import { gettagList } from '/@/api/tag/tag.ts';
import { h } from 'vue';
import { Tag } from 'ant-design-vue';
export const columns: BasicColumn[] = [
  {
    title: '文章标题',
    dataIndex: 'title',
    width: 100,
    align: 'left',
    sorter: false
  },
  // {
  //   title: '文章封面图',
  //   dataIndex: 'coverImage',
  //   width: 100,
  //   align: 'left',
  //   sorter: false
  // },
  {
    title: '分类专栏',
    dataIndex: 'categoryName',
    width: 100,
    align: 'left',
    sorter: false
  },
  {
    title: '文章类型',
    dataIndex: 'type',
    width: 60,
    customRender: ({ record }) => {
      const type = record.type;
      let color  = '#e33e33'
      let text = ''
      switch (type) {
        case 1:
          text = '原创'
        break;
        case 2:
          text = '转载'
        break;
        case 3:
          text = '翻译'
          break;
        default:
          break;
      }
      return h(Tag, { color: color }, () => text);
    },
  },
  {
    title: '状态',
    dataIndex: 'status',
    width: 60,
    customRender: ({ record }) => {
      const status = record.status;
      const enable = ~~status === 1;
      const color = enable ? 'green' : 'red';
      const text = enable ? '已发布' : '草稿';
      return h(Tag, { color: color }, () => text);
    },
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'title',
    label: '文章标题',
    component: 'Input',
    colProps: {
      span: 6,
    },
  },
  {
    field: 'category',
    label: '文章分类',
    component: 'ApiTreeSelect',
    componentProps: {
      api: getTree,
    },
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
    field: 'title',
    label: '',
    required: true,
    component: 'Input',
    slot:'SetTitle',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'content',
    label: '',
    required: true,
    component: 'Tinymce',
    componentProps: {
      height:700
    },
    colProps: {
      span: 24,
    },
  },
  {
    field: 'status',
    label: '',
    required: false,
    show:false,
    component: 'Input',
    colProps: {
      span: 12,
    },
  },
];
export const formSchemaEnd: FormSchema[] = [
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
  // {
  //   field: 'title',
  //   label: '文章标题',
  //   required: true,
  //   component: 'Input',
  //   show:false,
  //   colProps: {
  //     span: 24,
  //   },
  // },
  // {
  //   field: 'content',
  //   label: '文章内容',
  //   required: true,
  //   component: 'Tinymce',
  //   show:false,
  //   componentProps: {
  //     height:700
  //   },
  //   colProps: {
  //     span: 24,
  //   },
  // },
  // {
  //   field: 'status',
  //   label: '',
  //   required: false,
  //   show:false,
  //   component: 'Input',
  //   colProps: {
  //     span: 12,
  //   },
  // },
  {
    field: 'coverImage',
    label: '封面图',
    required: false,
    component: 'ImageUpload',
    componentProps: {
      count:1
    },
    colProps: {
      span: 24,
    },
  },
  {
    field: 'subTitle',
    label: '副标题',
    required: false,
    component: 'Input',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'summary',
    label: '摘要',
    required: false,
    component: 'InputTextArea',
    componentProps: {
      rows: 3,
      maxlength: 255,
      // placeholder:'摘要：会在推荐、列表等场景外露，帮助读者快速了解内容，支持一键将正文前 255 字符键入摘要文本框'
      placeholder:'摘要：会在推荐、列表等场景外露，帮助读者快速了解内容'
    },
    colProps: {
      span: 24,
    },
  },
  {
    field: 'articleTags',
    label: '标签',
    required: false,
    component: 'Select',
    slot:'SetTags',
    colProps: {
      span: 24,
    },
  },
  {
    field: 'categoryId',
    label: '分类专栏',
    required: true,
    component: 'ApiTreeSelect',
    componentProps: {
      labelInValue:true,
      api: getTree,
    },
    colProps: {
      span: 24,
    },
  },
  {
    field: 'categoryName',
    label: 'categoryName',
    required: true,
    component: 'Input',
    show:false,
    colProps: {
      span: 24,
    },
  },
  {
    field: 'type',
    label: '文章类型',
    required: false,
    component: 'RadioGroup',
    defaultValue:1,
    componentProps: {
      options: [
        {
          label: '原创',
          value:1
        },
        {
          label: '转载',
          value:2
        },
        {
          label: '翻译',
          value:3
        }
      ]
    },
    colProps: {
      span: 24,
    },
  },
];