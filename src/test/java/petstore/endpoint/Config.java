package petstore.endpoint;

public class Config {
    final static String BASE_URI = "https://petstore.swagger.io/v2/";
    final static String GET_PET_BY_ID = "pet/{petId}";
    final static String GET_PET_BY_STATUS = "pet/findByStatus";
    final static String DEL_PET_BY_ID = "pet/{petId}";
    final static String CREATE_PET = "pet";
    final static String UPDATE_PET = "pet";
    final static String CREATE_ORDER = "store/order";
    final static String GET_ORDER_BY_ID = "store/order/{orderId}";
    final static String DEL_ORDER_BY_ID = "/store/order/{orderId}";
    final static String UPLOAD_IMAGE_FOR_PET = "/pet/{petId}/uploadImage";
}
