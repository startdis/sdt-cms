import {defHttp} from '/@/utils/http/axios';
import {categoryInfo, categoryParams, categoryPageListGetResultModel, categoryPageParams} from "./model/categoryModel";
import {forEach} from "/@/utils/helper/treeHelper";
enum Api {
  categoryPageList = '/category/page',
  TreeList = '/category/tree',
  Insert = '/category/add',
  Update = '/category/update',
  Info = '/category/',
  Delete = '/category/delete',
}

export const getcategoryListByPage = (params?: categoryParams) => {
  return defHttp.postForm<categoryPageListGetResultModel>({ url: Api.categoryPageList, params });
};
export const getTree = (params?: categoryParams) => {
  const result = defHttp.post<categoryPageListGetResultModel>({url: Api.TreeList, params});
  return Promise.resolve(result).then(data => {
    const treeData = data
    forEach(treeData, (node) => {
      if (node.children && node.children.length === 0) {
        delete node.children;
      }
      node.key = node.id;
      node.value = node.id;
      node.title = node.name;
    }, { id: 'id', children: 'children', pid: 'parentId' });
    return treeData;
  });
};
export const Insert = (params?: categoryInfo) =>
  defHttp.post<categoryInfo>({url: Api.Insert, params});

export const getInfo = (id?: number | string) =>
  defHttp.get<categoryInfo>({url: Api.Info+id});
  
export const Update = (params?: categoryInfo) =>
  defHttp.put<categoryInfo>({url: Api.Update, params});

export const deleteByIds = (params?: Array<string>) =>
  defHttp.deleteForm<categoryInfo>({url: Api.Delete, params});
;