示例文件为gis_1.html

### 引用并配置Cesium
1. 需要引用CesiumJs，具体环境搭建请参考Cesium官网：
`https://cesium.com/cesiumjs/`
2. 引用BIMFACE的CesiumLoader.js


### 准确配置模型位置的方案
1. 由于各家地图经纬度有所区别，需要先定位一个大致的位置
`http://www.gpsspg.com/maps.htm`
通过上述链接先得到一个大致的经纬度
2. 加载模型以后，如果模型没有在需要的位置,可以通过接口调整
```JavaScript
var opt = {};
opt.longitude = 121.63688395557;
opt.latitude = 29.8397133345;
opt.rotation = 0;
opt.offsetHeight = 0;
cesiumManager.updateModelPosition(opt);
```
