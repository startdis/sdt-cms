import {defHttp} from '/@/utils/http/axios';
import {configInfo, configParams, configPageListGetResultModel, configPageParams} from "./model/configModel";

enum Api {
  configPageList = '/config/page',
  List = '/config/list',
  Insert = '/config/add',
  Update = '/config/update',
  UpdateBatch = '/config/updateBatch',
  Info = '/config/',
  Delete = '/config/delete',
}

export const getconfigListByPage = (params?: configParams) => {
  return defHttp.postForm<configPageListGetResultModel>({ url: Api.configPageList, params });
};
export const getconfigList = (params?: configParams) => {
  return defHttp.post<configPageListGetResultModel>({ url: Api.List, params });
};

export const Insert = (params?: configInfo) =>
  defHttp.post<configInfo>({url: Api.Insert, params});

export const getInfo = (id?: number | string) =>
  defHttp.get<configInfo>({url: Api.Info+id});
  
export const Update = (params?: configInfo) =>
  defHttp.put<configInfo>({ url: Api.Update, params });

export const UpdateBatch = (params?: configInfo) =>
  defHttp.put<configInfo>({url: Api.UpdateBatch, params});

export const deleteByIds = (params?: Array<string>) =>
  defHttp.deleteForm<configInfo>({url: Api.Delete, params});
;