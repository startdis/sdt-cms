import {defHttp} from '/@/utils/http/axios';
import {commentInfo, commentParams, commentPageListGetResultModel, commentPageParams} from "./model/commentModel";

enum Api {
  commentPageList = '/comment/page',
  Insert = '/comment/add',
  Update = '/comment/update',
  Info = '/comment/',
  Delete = '/comment/delete',
}

export const getcommentListByPage = (params?: commentParams) => {
  return defHttp.postForm<commentPageListGetResultModel>({ url: Api.commentPageList, params });
};

export const Insert = (params?: commentInfo) =>
  defHttp.post<commentInfo>({url: Api.Insert, params});

export const getInfo = (id?: number | string) =>
  defHttp.get<commentInfo>({url: Api.Info+id});
  
export const Update = (params?: commentInfo) =>
  defHttp.put<commentInfo>({url: Api.Update, params});

export const deleteByIds = (params?: Array<string>) =>
  defHttp.deleteForm<commentInfo>({url: Api.Delete, params});
;