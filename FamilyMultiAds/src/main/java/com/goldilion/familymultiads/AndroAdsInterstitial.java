package com.goldilion.familymultiads;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.AppLovinExtras;
import com.applovin.mediation.ApplovinAdapter;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class AndroAdsInterstitial {
    public static InterstitialAd mInterstitialAd;
    public static AdManagerInterstitialAd mAdManagerInterstitialAd;
    public static MaxInterstitialAd interstitialAd;
    public static int counter = 0;
    public static AppLovinInterstitialAdDialog interstitialAdlovin;
    public static AppLovinAd loadedAd;
    public static boolean irininter = false;

    public static void LoadInterstitial(Activity activity, String selectAds, String idInterstitial, String Hpk1, String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        switch (selectAds) {
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idInterstitial, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "APPLOVIN-M":
                if (idInterstitial == null){
                    interstitialAd = new MaxInterstitialAd("qwerty1234", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idInterstitial, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idInterstitial);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;

        }
    }

    public static void LoadInterstitialAdmob(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup, String Hpk1, String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        Bundle extras = new AppLovinExtras.Builder()
                .setMuteAudio(true)
                .build();
        AdRequest request = new AdRequest.Builder()
                //.addKeyword(Hpk1).addKeyword(Hpk2).addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                .build();
        InterstitialAd.load(activity, idInterstitial, request,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                if (idInterstitialBackup.equals("")) {
                    interstitialAd = new MaxInterstitialAd("qwerty12345", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idInterstitialBackup, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idInterstitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;

        }
    }

    public static void LoadInterstitialGoogleAds(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup) {

        AdManagerAdRequest adRequest =
                new AdManagerAdRequest.Builder()
                        .build();

        AdManagerInterstitialAd.load(activity,idIntertitial, adRequest,
                new AdManagerInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                        // The mAdManagerInterstitialAd reference will be null until
                        // an ad is loaded.
                        mAdManagerInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mAdManagerInterstitialAd = null;
                    }
                });


        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                if (idIntertitialBackup.equals("")) {
                    interstitialAd = new MaxInterstitialAd("qwerty12345", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idIntertitialBackup, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "IRON":
                IronSource.isInterstitialPlacementCapped(idIntertitialBackup);
                IronSource.loadInterstitial();
                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idIntertitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
        }
    }

    public static void LoadInterstitialApplovinDis(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup) {

        AdRequest.Builder builder = new AdRequest.Builder();
        Bundle interstitialExtras = new Bundle();
        interstitialExtras.putString("zone_id", idInterstitial);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                loadedAd = ad;
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                // Look at AppLovinErrorCodes.java for list of error codes.
            }
        });
        interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                if (idInterstitialBackup.equals("")) {
                    interstitialAd = new MaxInterstitialAd("qwerty12345", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idInterstitialBackup, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idInterstitial, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity,idInterstitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                // The mAdManagerInterstitialAd reference will be null until
                                // an ad is loaded.
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;

        }
    }

    public static void LoadInterstitialApplovinDisHPK(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup, String HPK1, String HPK2, String HPK3, String HPK4, String HPK5) {

        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(HPK1).addKeyword(HPK2).addKeyword(HPK3).addKeyword(HPK4).addKeyword(HPK5);
        Bundle interstitialExtras = new Bundle();
        interstitialExtras.putString("zone_id", idInterstitial);
        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);

        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                loadedAd = ad;
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                // Look at AppLovinErrorCodes.java for list of error codes.
            }
        });
        interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);

        switch (selectAdsBackup) {
            case "APPLOVIN-M":
                if (idInterstitialBackup.equals("")) {
                    interstitialAd = new MaxInterstitialAd("qwerty12345", activity);
                    interstitialAd.loadAd();
                } else {
                    interstitialAd = new MaxInterstitialAd(idInterstitialBackup, activity);
                    interstitialAd.loadAd();
                }

                break;
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idInterstitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity,idInterstitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                // The mAdManagerInterstitialAd reference will be null until
                                // an ad is loaded.
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;
        }
    }

    public static void LoadInterstitialApplovinMax(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup) {

        interstitialAd = new MaxInterstitialAd(idInterstitial, activity);
        interstitialAd.loadAd();

        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idInterstitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idInterstitial, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity,idInterstitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                // The mAdManagerInterstitialAd reference will be null until
                                // an ad is loaded.
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;

        }
    }

    public static void LoadInterstitialIron(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup) {

        IronSource.isInterstitialPlacementCapped(idInterstitial);
        IronSource.setInterstitialListener(new InterstitialListener() {
            /**
             * Invoked when Interstitial Ad is ready to be shown after load function was called.
             */
            @Override
            public void onInterstitialAdReady() {
                irininter = false;
            }

            /**
             * invoked when there is no Interstitial Ad available after calling load function.
             */
            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
                irininter = true;
            }

            /**
             * Invoked when the Interstitial Ad Unit is opened
             */
            @Override
            public void onInterstitialAdOpened() {
            }

            /*
             * Invoked when the ad is closed and the user is about to return to the application.
             */
            @Override
            public void onInterstitialAdClosed() {
            }

            /**
             * Invoked when Interstitial ad failed to show.
             * @param error - An object which represents the reason of showInterstitial failure.
             */
            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {

            }

            /*
             * Invoked when the end user clicked on the interstitial ad, for supported networks only.
             */
            @Override
            public void onInterstitialAdClicked() {
            }

            /** Invoked right before the Interstitial screen is about to open.
             *  NOTE - This event is available only for some of the networks.
             *  You should NOT treat this event as an interstitial impression, but rather use InterstitialAdOpenedEvent
             */
            @Override
            public void onInterstitialAdShowSucceeded() {
            }
        });
        IronSource.loadInterstitial();
        switch (selectAdsBackup) {
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder();
                Bundle interstitialExtras = new Bundle();
                interstitialExtras.putString("zone_id", idInterstitialBackup);
                builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd ad) {
                        loadedAd = ad;
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                    }
                });
                interstitialAdlovin = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                break;
            case "APPLOVIN-M":
                interstitialAd = new MaxInterstitialAd(idInterstitialBackup, activity);
                interstitialAd.loadAd();
                break;
            case "ADMOB":
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder()
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                InterstitialAd.load(activity, idInterstitialBackup, request,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mInterstitialAd = null;
                            }
                        });
                break;
            case "GOOGLE-ADS":
                AdManagerAdRequest adRequest =
                        new AdManagerAdRequest.Builder()
                                .build();

                AdManagerInterstitialAd.load(activity,idInterstitialBackup, adRequest,
                        new AdManagerInterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                                // The mAdManagerInterstitialAd reference will be null until
                                // an ad is loaded.
                                mAdManagerInterstitialAd = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                mAdManagerInterstitialAd = null;
                            }
                        });

                break;

        }
    }

    public static void ShowInterstitial(Activity activity, String selectAds, String idInterstitial, int interval, String Hpk1, String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {
            switch (selectAds) {
                case "ADMOB":
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(activity);
                        LoadInterstitial(activity, selectAds, idInterstitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
                    } else {
                        LoadInterstitial(activity, selectAds, idInterstitial, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
                    }
                    break;
                case "APPLOVIN-M":
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                        interstitialAd.loadAd();
                    } else {
                        interstitialAd.loadAd();
                    }
                    break;
                case "IRON":
                    IronSource.showInterstitial(idInterstitial);
                    break;
                case "APPLOVIN-D":
                    interstitialAdlovin.showAndRender(loadedAd);
                    break;
            }
            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowInterstitialAdmob(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup, int interval, String Hpk1, String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        if (counter >= interval) {

            if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
                LoadInterstitialAdmob(activity, selectAdsBackup, idInterstitial, idInterstitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                            interstitialAd.loadAd();
                        } else {
                            interstitialAd.loadAd();
                        }
                        break;
                    case "IRON":
                        IronSource.showInterstitial(idInterstitialBackup);
                        break;
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                }
                LoadInterstitialAdmob(activity, selectAdsBackup, idInterstitial, idInterstitialBackup, Hpk1, Hpk2, Hpk3, Hpk4, Hpk5);
            }

            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowInterstitialGoogleAds(Activity activity, String selectAdsBackup, String idIntertitial, String idIntertitialBackup,
                                                int interval) {
        if (counter >= interval) {
            if (mAdManagerInterstitialAd != null) {
                mAdManagerInterstitialAd.show(activity);
                LoadInterstitialGoogleAds(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                            interstitialAd.loadAd();
                        } else {
                            interstitialAd.loadAd();
                        }
                        break;
                    case "IRON":
                        IronSource.showInterstitial(idIntertitialBackup);
                        break;
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                }
                LoadInterstitialGoogleAds(activity, selectAdsBackup, idIntertitial, idIntertitialBackup);
            }

            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowInterstitialApplovinDis(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup, int interval) {
        if (counter >= interval) {
            if (interstitialAdlovin != null) {
                AppLovinAdDisplayListener listener = new AppLovinAdDisplayListener() {
                    @Override
                    public void adDisplayed(AppLovinAd ad) {

                    }

                    @Override
                    public void adHidden(AppLovinAd ad) {
                        switch (selectAdsBackup) {
                            case "APPLOVIN-M":
                                if (interstitialAd.isReady()) {
                                    interstitialAd.showAd();
                                    interstitialAd.loadAd();
                                } else {
                                    interstitialAd.loadAd();
                                }
                                break;
                            case "IRON":
                                IronSource.showInterstitial(idInterstitialBackup);
                                break;
                            case "ADMOB":
                                if (mInterstitialAd != null) {
                                    mInterstitialAd.show(activity);
                                }
                                break;
                            case "GOOGLE-ADS":
                                if (mAdManagerInterstitialAd != null) {
                                    mAdManagerInterstitialAd.show(activity);
                                }
                                break;
                        }
                        LoadInterstitialApplovinDis(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
                    }
                };
                interstitialAdlovin.setAdDisplayListener(listener);
                interstitialAdlovin.showAndRender(loadedAd);
                LoadInterstitialApplovinDis(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
            }

            counter = 0;
        } else {
            counter++;
        }

    }

    public static void ShowInterstitialApplovinDisHPK(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup, int interval,String HPK1, String HPK2, String HPK3, String HPK4, String HPK5) {
        if (counter >= interval) {
            if (interstitialAdlovin != null) {
                AppLovinAdDisplayListener listener = new AppLovinAdDisplayListener() {
                    @Override
                    public void adDisplayed(AppLovinAd ad) {

                    }

                    @Override
                    public void adHidden(AppLovinAd ad) {
                        switch (selectAdsBackup) {
                            case "APPLOVIN-M":
                                if (interstitialAd.isReady()) {
                                    interstitialAd.showAd();
                                    interstitialAd.loadAd();
                                } else {
                                    interstitialAd.loadAd();
                                }
                                break;
                             case "IRON":
                                IronSource.showInterstitial(idInterstitialBackup);
                                break;
                            case "ADMOB":
                                if (mInterstitialAd != null) {
                                    mInterstitialAd.show(activity);
                                }
                                break;
                        }
                        LoadInterstitialApplovinDisHPK(activity, selectAdsBackup, idInterstitial, idInterstitialBackup, HPK1, HPK2, HPK3, HPK4, HPK5);
                    }
                };
                interstitialAdlovin.setAdDisplayListener(listener);
                interstitialAdlovin.showAndRender(loadedAd);
                LoadInterstitialApplovinDisHPK(activity, selectAdsBackup, idInterstitial, idInterstitialBackup, HPK1, HPK2, HPK3, HPK4, HPK5);
            }

            counter = 0;
        } else {
            counter++;
        }

    }

    public static void ShowInterstitialApplovinMax(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup, int interval) {
        if (counter >= interval) {
            if (interstitialAd.isReady()) {
                interstitialAd.showAd();
                LoadInterstitialApplovinMax(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
            } else {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "IRON":
                        IronSource.showInterstitial(idInterstitialBackup);
                        break;
                    case "ADMOB":
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (mAdManagerInterstitialAd != null) {
                            mAdManagerInterstitialAd.show(activity);
                        }
                        break;
                }
                LoadInterstitialApplovinMax(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
                interstitialAd.loadAd();
            }

            counter = 0;
        } else {
            counter++;
        }
    }

    public static void ShowInterstitialIron(Activity activity, String selectAdsBackup, String idInterstitial, String idInterstitialBackup, int interval) {
        if (counter >= interval) {
            if (irininter) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-D":
                        if (interstitialAdlovin != null) {
                            interstitialAdlovin.showAndRender(loadedAd);
                        }
                        break;
                    case "APPLOVIN-M":
                        if (interstitialAd.isReady()) {
                            interstitialAd.showAd();
                        }
                        break;
                    case "ADMOB":
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }
                        break;
                    case "GOOGLE-ADS":
                        if (mAdManagerInterstitialAd != null) {
                            mAdManagerInterstitialAd.show(activity);
                        }
                        break;
                }
            } else {
                IronSource.showInterstitial(idInterstitial);
            }

            LoadInterstitialIron(activity, selectAdsBackup, idInterstitial, idInterstitialBackup);
            counter = 0;
        } else {
            counter++;
        }

    }

}
