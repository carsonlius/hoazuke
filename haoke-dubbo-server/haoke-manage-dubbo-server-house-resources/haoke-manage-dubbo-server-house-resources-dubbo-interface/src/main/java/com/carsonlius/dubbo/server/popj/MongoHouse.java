package com.carsonlius.dubbo.server.popj;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(value = "house")
@AllArgsConstructor
@NoArgsConstructor
public class MongoHouse  implements java.io.Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId id;

    private Long hid;

    private String title;

    private Float[] loc;

}
