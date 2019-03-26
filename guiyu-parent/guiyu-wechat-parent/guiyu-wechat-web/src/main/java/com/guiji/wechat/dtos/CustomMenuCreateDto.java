package com.guiji.wechat.dtos;

import com.google.common.collect.Lists;

import java.util.List;

public class CustomMenuCreateDto {
    /**
     * 1、click：点击推事件用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
     * 2、view：跳转URL用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。
     * 3、scancode_push：扫码推事件用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。
     * 4、scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息。
     * 5、pic_sysphoto：弹出系统拍照发图用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息。
     * 6、pic_photo_or_album：弹出拍照或者相册发图用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
     * 7、pic_weixin：弹出微信相册发图器用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。
     * 8、location_select：弹出地理位置选择器用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。
     * 9、media_id：下发消息（除文本消息）用户点击media_id类型按钮后，微信服务器会将开发者填写的永久素材id对应的素材下发给用户，永久素材类型可以是图片、音频、视频、图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
     * 10、view_limited：跳转图文消息URL用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，永久素材类型只支持图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
     */

    private List<Button> button = Lists.newArrayList();

    public static CustomMenuCreateDto build(){
        return new CustomMenuCreateDto();
    }

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    public CustomMenuCreateDto addButton(Button button){
        this.button.add(button);
        return this;
    }

    public static class Button{

        /**
         * 必填参数
         * 菜单标题，不超过16个字节，子菜单不超过60个字节
         */
        private String name;

        /**
         * 必填参数
         * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
         */
        private String type;

        /**
         * view、miniprogram类型必须
         * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
         */
        private String url;


        /**
         * click等点击类型必须
         * 菜单KEY值，用于消息接口推送，不超过128字节
         */
        private String key;

        /**
         * miniprogram类型必须
         * 小程序的appid（仅认证公众号可配置）
         */
        private String appid;

        /**
         * miniprogram类型必须
         * 小程序的页面路径
         */
        private String pagepath;

        /**
         * media_id类型和view_limited类型必须
         * 调用新增永久素材接口返回的合法media_id
         */
        private String media_id;

        /**
         * 二级菜单数组，个数应为1~5个
         */
        private List<Button> sub_button;


        public Button() {
        }

        public static Button build(){
            return new Button();
        }

        public String getName() {
            return name;
        }

        public Button setName(String name) {
            this.name = name;
            return this;
        }

        public String getType() {
            return type;
        }

        public Button setType(String type) {
            this.type = type;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Button setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getKey() {
            return key;
        }

        public Button setKey(String key) {
            this.key = key;
            return this;
        }

        public String getAppid() {
            return appid;
        }

        public Button setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public String getPagepath() {
            return pagepath;
        }

        public Button setPagepath(String pagepath) {
            this.pagepath = pagepath;
            return this;
        }

        public String getMedia_id() {
            return media_id;
        }

        public Button setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }

        public List<Button> getSub_button() {
            return sub_button;
        }

        public void setSub_button(List<Button> sub_button) {
            this.sub_button = sub_button;
        }
    }
}
