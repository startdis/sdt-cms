import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel'

export type tagParams = {
  name?: string;
  status?:number;
};

export type tagPageParams = BasicPageParams & tagParams;

export type tagInfo = {
    id?: 'string';
    name?: 'string';
    tagName?: 'string';
    tagId?: 'string';
    status?: number;
    deleted?: 'string';
    revision?: 'string';
    groupTenantId?: 'string';
    companyTenantId?: 'string';
    createdBy?: 'string';
    createdAt?: 'string';
    updatedBy?: 'string';
    updatedAt?: 'string';
  };

/**
 * @description: Request list return value
 */
export type tagPageListGetResultModel = BasicFetchResult<tagInfo>;