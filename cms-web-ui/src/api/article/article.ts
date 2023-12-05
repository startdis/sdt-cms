import {defHttp} from '/@/utils/http/axios';
import {articleInfo, articleParams, articlePageListGetResultModel, articlePageParams} from "./model/articleModel";

enum Api {
  articlePageList = '/article/page',
  Insert = '/article/add',
  Update = '/article/update',
  Info = '/article/',
  Delete = '/article/delete',
}

export const getarticleListByPage = (params?: articleParams) => {
  return defHttp.postForm<articlePageListGetResultModel>({ url: Api.articlePageList, params });
};

export const Insert = (params?: articleInfo) =>
  defHttp.post<articleInfo>({url: Api.Insert, params});

export const getInfo = (id?: number | string) =>
  defHttp.get<articleInfo>({url: Api.Info+id});
  
export const Update = (params?: articleInfo) =>
  defHttp.put<articleInfo>({url: Api.Update, params});

export const deleteByIds = (params?: Array<string>) =>
  defHttp.deleteForm<articleInfo>({url: Api.Delete, params});
;