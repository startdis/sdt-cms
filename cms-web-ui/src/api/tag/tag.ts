import {defHttp} from '/@/utils/http/axios';
import {tagInfo, tagParams, tagPageListGetResultModel, tagPageParams} from "./model/tagModel";

enum Api {
  tagPageList = '/tag/page',
  tagList = '/tag/list',
  Insert = '/tag/add',
  Update = '/tag/update',
  Info = '/tag/',
  Delete = '/tag/delete',
}

export const gettagListByPage = (params?: tagParams) => {
  return defHttp.postForm<tagPageListGetResultModel>({ url: Api.tagPageList, params });
};
export const gettagList = (params?: tagParams) => {
  return defHttp.post<tagPageListGetResultModel>({ url: Api.tagList, params });
};

export const Insert = (params?: tagInfo) =>
  defHttp.post<tagInfo>({url: Api.Insert, params});

export const getInfo = (id?: number | string) =>
  defHttp.get<tagInfo>({url: Api.Info+id});
  
export const Update = (params?: tagInfo) =>
  defHttp.put<tagInfo>({url: Api.Update, params});

export const deleteByIds = (params?: Array<string>) =>
  defHttp.deleteForm<tagInfo>({url: Api.Delete, params});
;