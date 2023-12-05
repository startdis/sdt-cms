import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel'

export type commentParams = {
  keyword?: string;
};

export type commentPageParams = BasicPageParams & commentParams;

export type commentInfo = {
    id?: 'string';
    parentId?: 'string';
    articleId?: 'string';
    commentator?: 'string';
    phone?: 'string';
    email?: 'string';
    ip?: 'string';
    website?: 'string';
    content?: 'string';
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
export type commentPageListGetResultModel = BasicFetchResult<commentInfo>;