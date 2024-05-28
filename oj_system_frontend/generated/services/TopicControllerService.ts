import type { BaseResponse_boolean_ } from "../models/BaseResponse_boolean_";
import type { BaseResponse_long_ } from "../models/BaseResponse_long_";
import type { BaseResponse_Page_Topic_ } from "../models/BaseResponse_Page_Topic_";
import type { BaseResponse_Page_TopicVO_ } from "../models/BaseResponse_Page_TopicVO_";
import type { BaseResponse_TopicVO_ } from "../models/BaseResponse_TopicVO_";
import type { DeleteRequest } from "../models/DeleteRequest";
import type { TopicAddRequest } from "../models/TopicAddRequest";
import type { TopicEditRequest } from "../models/TopicEditRequest";
import type { TopicQueryRequest } from "../models/TopicQueryRequest";
import type { TopicUpdateRequest } from "../models/TopicUpdateRequest";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";
export class TopicControllerService {
  /**
   * addTopic
   * @param topicAddRequest topicAddRequest
   * @returns BaseResponse_long_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static addTopicUsingPost(
    topicAddRequest: TopicAddRequest
  ): CancelablePromise<BaseResponse_long_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic/add",
      body: topicAddRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
  /**
   * deleteTopic
   * @param deleteRequest deleteRequest
   * @returns BaseResponse_boolean_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static deleteTopicUsingPost(
    deleteRequest: DeleteRequest
  ): CancelablePromise<BaseResponse_boolean_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic/delete",
      body: deleteRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
  /**
   * editTopic
   * @param topicEditRequest topicEditRequest
   * @returns BaseResponse_boolean_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static editTopicUsingPost(
    topicEditRequest: TopicEditRequest
  ): CancelablePromise<BaseResponse_boolean_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic/edit",
      body: topicEditRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
  /**
   * getTopicVOById
   * @param id id
   * @returns BaseResponse_TopicVO_ OK
   * @throws ApiError
   */
  public static getTopicVoByIdUsingGet(
    id?: number
  ): CancelablePromise<BaseResponse_TopicVO_> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/api/topic/get/vo",
      query: {
        id: id,
      },
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
  /**
   * listTopicByPage
   * @param topicQueryRequest topicQueryRequest
   * @returns BaseResponse_Page_Topic_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static listTopicByPageUsingPost(
    topicQueryRequest: TopicQueryRequest
  ): CancelablePromise<BaseResponse_Page_Topic_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic/list/page",
      body: topicQueryRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
  /**
   * listTopicVOByPage
   * @param topicQueryRequest topicQueryRequest
   * @returns BaseResponse_Page_TopicVO_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static listTopicVoByPageUsingPost(
    topicQueryRequest: TopicQueryRequest
  ): CancelablePromise<BaseResponse_Page_TopicVO_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic/list/page/vo",
      body: topicQueryRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
  /**
   * listMyTopicVOByPage
   * @param topicQueryRequest topicQueryRequest
   * @returns BaseResponse_Page_TopicVO_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static listMyTopicVoByPageUsingPost(
    topicQueryRequest: TopicQueryRequest
  ): CancelablePromise<BaseResponse_Page_TopicVO_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic/my/list/page/vo",
      body: topicQueryRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
  /**
   * updateTopic
   * @param topicUpdateRequest topicUpdateRequest
   * @returns BaseResponse_boolean_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static updateTopicUsingPost(
    topicUpdateRequest: TopicUpdateRequest
  ): CancelablePromise<BaseResponse_boolean_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic/update",
      body: topicUpdateRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
}
