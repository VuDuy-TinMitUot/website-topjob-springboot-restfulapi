package com.vuduy.restfulapi.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.vuduy.restfulapi.domain.RestReponse;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FormatRestReponse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // TODO Auto-generated method stub
        return true;
        /*
         * Khi nào chúng ta muốn ghi đè lại, format lại phản hồi của API?
         * - Ở đây ta muốn bất cứ phản hồi nào cũng ghi đè hết nên (return true).
         * ------------>nếu (return true) nó sẽ chạy xuống hàm (beforBodyWrite).
         * - sau này phụ thuộc từng controller thì dùng if-else trả ra (return false)
         * thì nó không format phản hồi API nữa.
         */
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(
            Object body, // data phản hồi của API (Chưa format)
            MethodParameter retuParameter,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request, // request: lời gọi gửi lên từ phía server
            ServerHttpResponse response) { // response: phản hồi gửi về cho client

        // Gọi Servlet để gọi STATUS mã phản hồi cho RESPONSE
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = servletResponse.getStatus(); // trả về mã lỗi

        RestReponse<Object> res = new RestReponse<Object>(); // Khởi tạo đối tượng res
        res.setStatusCode(status); // Sử dụng phương thức setStatusCode để lưu mã lỗi (status),và xuất do return
                                   // res

        if (body instanceof String) { // Kiểm tra dữ liệu của body,Nếu là String thì sẽ thực hiện khối lệnh bỏ qua
            return true;
        }

        // CASE ERROR
        if (status >= 400) {
            return body; // Khi sai, controller chạy về (GlobalException), trả về dữ liệu được format sẵn
                         // của (RestReponse.java), và dữ liệu được truyền vào (return body)
        } else {
            res.setData(body);// Xuất do return res;
            res.setMessage("CALL API SUCCESS");// Xuất do return res;
        }
        return res;
    }

}
