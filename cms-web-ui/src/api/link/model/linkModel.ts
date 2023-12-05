import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel'

export type linkParams = {
  keyword?: string;
};

export type linkPageParams = BasicPageParams & linkParams;

export type linkInfo = {
    id?: 'string';
    name?: 'string';
    url?: 'string';
    description?: 'string';
    sort?: 'string';
    type?: 'string';
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
export type linkPageListGetResultModel = BasicFetchResult<linkInfo>;