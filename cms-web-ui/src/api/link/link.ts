import {defHttp} from '/@/utils/http/axios';
import {linkInfo, linkParams, linkPageListGetResultModel, linkPageParams} from "./model/linkModel";

enum Api {
  linkPageList = '/link/page',
  Insert = '/link/add',
  Update = '/link/update',
  Info = '/link/',
  Delete = '/link/delete',
}

export const getlinkListByPage = (params?: linkParams) => {
  return defHttp.postForm<linkPageListGetResultModel>({ url: Api.linkPageList, params });
};

export const Insert = (params?: linkInfo) =>
  defHttp.post<linkInfo>({url: Api.Insert, params});

export const getInfo = (id?: number | string) =>
  defHttp.get<linkInfo>({url: Api.Info+id});
  
export const Update = (params?: linkInfo) =>
  defHttp.put<linkInfo>({url: Api.Update, params});

export const deleteByIds = (params?: Array<string>) =>
  defHttp.deleteForm<linkInfo>({url: Api.Delete, params});
;