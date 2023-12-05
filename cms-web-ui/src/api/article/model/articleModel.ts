import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel'

export type articleParams = {
  keyword?: string;
};

export type articlePageParams = BasicPageParams & articleParams;

export type articleInfo = {
    id?: 'string';
    title?: 'string';
    subTitle?: 'string';
    customUrl?: 'string';
    coverImage?: 'string';
    content?: 'string';
    articleTags?:string;
    coverImageList?:any;
    category?: 'string';
    tags?: 'string';
    views?: 'string';
    comment?: 'string';
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
export type articlePageListGetResultModel = BasicFetchResult<articleInfo>;