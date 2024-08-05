package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Contact    {

    public String name;
    public String lastName;
    public String phone;
    public String email;
    public String address;
    public String description;

}
