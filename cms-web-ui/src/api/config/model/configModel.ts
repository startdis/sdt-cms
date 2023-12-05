import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel'

export type configParams = {
  keyword?: string;
};

export type configPageParams = BasicPageParams & configParams;

export type configInfo = {
    id?: 'string';
    type?: 'string';
    code?: 'string';
    name?: 'string';
    value?: 'string';
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
export type configPageListGetResultModel = BasicFetchResult<configInfo>;