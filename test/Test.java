import java.util.ArrayList;
import java.util.List;

import com.ewing.busi.order.dto.SubmitCartReq;
import com.ewing.busi.order.dto.SubmitCartReq.Item;
import com.google.gson.Gson;

public class Test {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        SubmitCartReq req = new SubmitCartReq();

        Item item = req.new Item();
        item.setId(1);
        item.setItemCount(10);

        Item item1 = req.new Item();
        item1.setId(11);
        item1.setItemCount(20);
        req.getList().add(item);
        req.getList().add(item1);

        CommonJson<SubmitCartReq> cj = new CommonJson<SubmitCartReq>();

        cj.setData(req);

        String res1 = cj.toJson(SubmitCartReq.class);
        System.out.println(res1);

        String json = "{data:{list:[{id:1,itemCount:10},{id:11,itemCount:20}]}}";
        String json1 = "{list:[{id:1,itemCount:10},{id:11,itemCount:20}]}";
        CommonJson<SubmitCartReq> re = CommonJson.fromJson(json, SubmitCartReq.class);
        Gson gson = new Gson();
        SubmitCartReq res = gson.fromJson(json1, SubmitCartReq.class);
        
        System.out.println(re);
    }
}