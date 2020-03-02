package com.ontob.greendaodemo.goodsmodule;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GoodsModule implements Parcelable {
    @Id(autoincrement = true)
    private Long id;
    private Integer goodsId;
    private String name;
    private String icon;
    private String info;
    private String type;
    @Generated(hash = 1110492143)
    public GoodsModule(Long id, Integer goodsId, String name, String icon,
            String info, String type) {
        this.id = id;
        this.goodsId = goodsId;
        this.name = name;
        this.icon = icon;
        this.info = info;
        this.type = type;
    }
    @Generated(hash = 1167747865)
    public GoodsModule() {
    }

    protected GoodsModule(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            goodsId = null;
        } else {
            goodsId = in.readInt();
        }
        name = in.readString();
        icon = in.readString();
        info = in.readString();
        type = in.readString();
    }

    public static final Creator<GoodsModule> CREATOR = new Creator<GoodsModule>() {
        @Override
        public GoodsModule createFromParcel(Parcel in) {
            return new GoodsModule(in);
        }

        @Override
        public GoodsModule[] newArray(int size) {
            return new GoodsModule[size];
        }
    };

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        if (goodsId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(goodsId);
        }
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeString(info);
        dest.writeString(type);
    }
}
