package com.is.health.upload;

import android.os.Handler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/6/27.
 */
public class MultipartBuilder {

    public static Map<String, RequestBody> pathsToMapRequestBody(List<String> files,Handler mHandler){
        Map<String, RequestBody> requestBodyMap=new HashMap<String, RequestBody>();
        for (int i=0;i<files.size(); i++) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            File img = new File(files.get(i));
            if (img.exists()) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), img);
                UploadFileRequestBody fileRequestBody = new UploadFileRequestBody(requestBody, new DefaultProgressListener(mHandler,i));
                requestBodyMap.put("file\"; filename=\"" + img.getName(),fileRequestBody);
            }
        }
        return requestBodyMap;
    }
    /**
     * 把File对象转化成MultipartBody
     * @param files
     * @return
     */
    public static MultipartBody pathsToMultipartBody(List<String> files,Handler mHandler) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (int i=0;i<files.size(); i++) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            File img=new File(files.get(i));
            if (img.exists()){

                RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), img);

/*                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("file", img.getName(), RequestBody.create(null, img))
                        .build();*/

               // UploadFileRequestBody fileRequestBody = new UploadFileRequestBody(requestBody, new DefaultProgressListener(mHandler,i));
                builder.addFormDataPart("file", img.getName(), requestBody);
            }
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }
    /**
     * 把File对象转化成MultipartBody
     * @param files
     * @return
     */
    public static List<MultipartBody.Part> pathsToMultipartBodyParts(List<String> files,Handler mHandler) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (int i=0;i<files.size(); i++) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            File img=new File(files.get(i));
            if (img.exists()){
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), img);
                UploadFileRequestBody fileRequestBody = new UploadFileRequestBody(requestBody, new DefaultProgressListener(mHandler,i));
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", img.getName(), fileRequestBody);
                parts.add(part);
            }
        }

        return parts;
    }

    /**
     * 把File对象转化成MultipartBody
     * @param files
     * @return
     */
    public static MultipartBody filesToMultipartBody(List<File> files,Handler mHandler) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (int i=0;i<files.size(); i++) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), files.get(i));
            UploadFileRequestBody fileRequestBody = new UploadFileRequestBody(requestBody, new DefaultProgressListener(mHandler,i));
            builder.addFormDataPart("file", files.get(i).getName(), fileRequestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    /**
     * 把File转化成MultipartBody.Part
     * @param files
     * @return
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files,Handler mHandler) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (int i=0;i<files.size(); i++) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), files.get(i));
            UploadFileRequestBody fileRequestBody = new UploadFileRequestBody(requestBody, new DefaultProgressListener(mHandler,i));
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", files.get(i).getName(), fileRequestBody);
            parts.add(part);
        }
        return parts;
    }
}
