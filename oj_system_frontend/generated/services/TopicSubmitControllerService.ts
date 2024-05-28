import type { BaseResponse_long_ } from "../models/BaseResponse_long_";
import type { BaseResponse_Page_TopicSubmitVO_ } from "../models/BaseResponse_Page_TopicSubmitVO_";
import type { TopicSubmitAddRequest } from "../models/TopicSubmitAddRequest";
import type { TopicSubmitQueryRequest } from "../models/TopicSubmitQueryRequest";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";
export class TopicSubmitControllerService {
  /**
   * doTopicSubmit
   * @param topicSubmitAddRequest topicSubmitAddRequest
   * @returns BaseResponse_long_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static doTopicSubmitUsingPost(
    topicSubmitAddRequest: TopicSubmitAddRequest
  ): CancelablePromise<BaseResponse_long_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic_submit/",
      body: topicSubmitAddRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
  /**
   * listTopicSubmitByPage
   * @param topicSubmitQueryRequest topicSubmitQueryRequest
   * @returns BaseResponse_Page_TopicSubmitVO_ OK
   * @returns any Created
   * @throws ApiError
   */
  public static listTopicSubmitByPageUsingPost(
    topicSubmitQueryRequest: TopicSubmitQueryRequest
  ): CancelablePromise<BaseResponse_Page_TopicSubmitVO_ | any> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/api/topic_submit/list/page",
      body: topicSubmitQueryRequest,
      errors: {
        401: `Unauthorized`,
        403: `Forbidden`,
        404: `Not Found`,
      },
    });
  }
}
