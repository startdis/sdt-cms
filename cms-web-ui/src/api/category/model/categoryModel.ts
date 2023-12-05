import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel'

export type categoryParams = {
  keyword?: string;
};

export type categoryPageParams = BasicPageParams & categoryParams;

export type categoryInfo = {
    id?: 'string';
    parentId?: 'string';
    name?: 'string';
    icon?: 'string';
    sort?: 'string';
    status?: 'string';
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
export type categoryPageListGetResultModel = BasicFetchResult<categoryInfo>;