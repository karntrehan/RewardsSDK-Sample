# Integration Guide
### Android Rewards SDK - m.Paani

### Introduction
The Rewards SDK (SDK) is a “Gift Recommendation and Redemption SDK” provided to partners (Partners) of m.Paani. The SDK is to be incorporated into your Android apps, allowing your users to view m.Paani gifts and redeem the gifts they like as well as are eligible for. 

This sample app demonstrates how partners can integrate the RewardsSDK into their apps.

#### SDK tech specifications
The SDK is written completely in native Java and XML following the MVP architecture.
It contains the following dependencies:
 - Support Libraries
    - `appcompat-v7`
    - `support-v4`
    - `recyclerview-v7`
    - `cardview-v7`
    - `design`
    - `support-v4`
 - Dagger
 - OkHttp3
 - Gson
 - Picasso
 - AdapterDelegate
 - ViewPagerIndicator

 It contains the following **permissions**:
 - INTERNET

Min SDK - **14+** 


**Dependencies and Permissions are minimal**: *We have kept the dependencies and permissions at the minimum to leave the least footprint on your app. We also love good, performant apps as much as you do.*

#### Environments
We support 2 environments:
 - **Staging** - Used during development.
 - **Production** - Used in production ready apps.

Both staging and production would have the same code and flow, just separate DBs.

**Staging can be volatile**: *Although we try our level best to maintain 100% uptime of the staging environment, we cannot guarantee the same. Also the test data being entered by you at staging environment could, scarcely, be deleted during maintenance and upgrades of our staging environment.*

#### Pre-Requisites

 - **Test User needs to be registered**: *Look at the Server Integration guide to know how to register users and give them points.*
 - **You need to have an API key**:  *We provide 2 sets of keys. One for production and one for staging. If you haven’t received it yet, request for one right now!*

### Integration
Integrating the SDK into your app is a 6 step process.

1. Get an API key from us.

2. Add the API key to your `AndroidManifest.xml` under the `application ` node.
Example:
``` xml
    <application ... >
        <meta-data
            android:name="com.mpaani.rewards.ApiKey"
            android:value="{APIKEY}" />
        <activity... >
    </application>
```

3. Integrate the SDK into your app.
Add these lines to your module level (Ex: app) `build.gradle` file.
```gradle
repositories {
    maven { url "http://dl.bintray.com/mpaani/Rewards" }
}
```


```gradle
dependencies {
	....
	compile 'com.mpaani.rewards:rewards:0.9.0'
}
```
*Make sure to use the latest version of the SDK*
 [ ![Download](https://api.bintray.com/packages/mpaani/Rewards/rewards/images/download.svg) ](https://bintray.com/mpaani/Rewards/rewards/_latestVersion)

4. Implement the `MPRedemptionResponseListener` in the Activity from where the user can start the flow of viewing and redeeming rewards. The listener has 2 callbacks.

    - `redemptionSuccess(TGiftSuccess tGiftSuccess)` : Callback that the redemption flow was complete. Contains:
     	- `rewardRedeemed` - Information about the reward redeemed.
        - `memberBalance` - New member balance after redemption.
        - `redemptionRequestId` - Redemption request id fron m.Paani

    - `redemptionFailed(TGiftFailed tGiftFailed)`: Callback that the redemption process failed due to various reasons. Reasons include invalid phonenumber, invalid key, etc. Contains:
       - `failCode` - Failure reason code
       - `failedReason` - Failure reason message

5. Add the following line to initialize the SDK flow:
```java
Rewards.getInstance().build(context, "9898989898", this);
```
 - `context`: Context of the current activity
 - `9898989898`: Replace with the phonenumber of the member
 - `this`: Instance of the `MPRedemptionResponseListener`

*Make sure the phonenumber entered by you is already registered with us using the server API.*

6. You will recieve the result of the redemption flow in the callbacks as follows:
```java
    @Override
    public void redemptionSuccess(TGiftSuccess tGiftSuccess) {
        Toast.makeText(this, tGiftSuccess.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redemptionFailed(TGiftFailed tGiftFailed) {
        Toast.makeText(this, tGiftFailed.toString(), Toast.LENGTH_SHORT).show();
    }
```


#### Complete Example

A complete working example can found in the [RSampleActivity.java](https://github.com/mpaani/RewardsSDK-Sample/blob/master/app/src/main/java/com/mpaani/rsample/RSampleActivity.java)


#### Customization
You can change the colors of the SDK to match your app's theme. To change the colors, add the following colors to your `colors.xml` file
```xml
    <color name="r_colorPrimary">#0179C3</color>
    <color name="r_colorPrimaryDark">#0065A3</color>
    <color name="r_colorPrimaryLight">#F0F7FC</color>
    <color name="r_colorAccent">#FF4357</color>
    <color name="r_colorAccent_faded">#99FF4357</color>
```

##### Queries
If you have any issues with the SDK integration, kindly send us an email on `karan@mpaani.com` and we shall get back to you as soon as possible.
