package com.goldilion.familymultiads;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

import java.util.Map;

public class AndroAdsReward {
    public static MaxRewardedAd rewardedAd;
    public static boolean unlockreward = false;
    public static AppLovinIncentivizedInterstitial incentivizedInterstitial;
    private static RewardedAd mRewardedAd;

    public static void LoadReward(Activity activity, String selectAds, String idReward) {
        switch (selectAds) {
            case "ADMOB":
                AdRequest adRequest = new AdRequest.Builder().build();
                RewardedAd.load(activity, idReward,
                        adRequest, new RewardedAdLoadCallback() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                mRewardedAd = null;
                            }

                            @Override
                            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                mRewardedAd = rewardedAd;

                            }
                        });

                break;
            case "APPLOVIN-M":
                rewardedAd = MaxRewardedAd.getInstance(idReward, activity);
                rewardedAd.loadAd();
                MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                    @Override
                    public void onRewardedVideoStarted(MaxAd ad) {

                    }

                    @Override
                    public void onRewardedVideoCompleted(MaxAd ad) {
                        unlockreward = true;
                    }

                    @Override
                    public void onUserRewarded(MaxAd ad, MaxReward reward) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {

                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {

                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {

                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                };
                rewardedAd.setListener(maxRewardedAdListener);
                break;
            case "APPLOVIN-D":
                incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idReward, AppLovinSdk.getInstance(activity));
                incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd appLovinAd) {
                        // A rewarded video was successfully received.
                    }
                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // A rewarded video failed to load.
                    }
                });
                break;

        }
    }

    public static void LoadRewardAdmob(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(activity, idReward,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;

                    }
                });
        switch (selectBackupAds) {
            case "APPLOVIN-M":
                rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                rewardedAd.loadAd();
                MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                    @Override
                    public void onRewardedVideoStarted(MaxAd ad) {

                    }

                    @Override
                    public void onRewardedVideoCompleted(MaxAd ad) {
                        unlockreward = true;
                    }

                    @Override
                    public void onUserRewarded(MaxAd ad, MaxReward reward) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {

                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {

                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {

                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                };
                rewardedAd.setListener(maxRewardedAdListener);
                break;
            case "APPLOVIN-D":
                incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd appLovinAd) {
                        // A rewarded video was successfully received.
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // A rewarded video failed to load.
                    }
                });
                break;

            case "IRON":
                IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                    @Override
                    public void onRewardedVideoAdOpened() {
                    }

                    @Override
                    public void onRewardedVideoAdClosed() {
                    }

                    @Override
                    public void onRewardedVideoAvailabilityChanged(boolean available) {
                    }

                    @Override
                    public void onRewardedVideoAdRewarded(Placement placement) {
                        unlockreward = true;
                    }

                    @Override
                    public void onRewardedVideoAdShowFailed(IronSourceError error) {
                    }

                    @Override
                    public void onRewardedVideoAdClicked(Placement placement) {
                    }

                    @Override
                    public void onRewardedVideoAdStarted() {
                    }

                    @Override
                    public void onRewardedVideoAdEnded() {
                    }
                });
                break;

        }
    }

    public static void LoadRewardApplovinMax(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        rewardedAd = MaxRewardedAd.getInstance(idReward, activity);
        rewardedAd.loadAd();
        MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
            @Override
            public void onRewardedVideoStarted(MaxAd ad) {

            }

            @Override
            public void onRewardedVideoCompleted(MaxAd ad) {
                unlockreward = true;
            }

            @Override
            public void onUserRewarded(MaxAd ad, MaxReward reward) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        };
        rewardedAd.setListener(maxRewardedAdListener);
        switch (selectBackupAds) {
            case "ADMOB":
                AdRequest adRequest = new AdRequest.Builder().build();
                RewardedAd.load(activity, idBackupReward,
                        adRequest, new RewardedAdLoadCallback() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                mRewardedAd = null;
                            }

                            @Override
                            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                mRewardedAd = rewardedAd;

                            }
                        });

                break;
            case "APPLOVIN-D":
                incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd appLovinAd) {
                        // A rewarded video was successfully received.
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        // A rewarded video failed to load.
                    }
                });
                break;

            case "IRON":
                IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                    @Override
                    public void onRewardedVideoAdOpened() {
                    }

                    @Override
                    public void onRewardedVideoAdClosed() {
                    }

                    @Override
                    public void onRewardedVideoAvailabilityChanged(boolean available) {
                    }

                    @Override
                    public void onRewardedVideoAdRewarded(Placement placement) {
                        unlockreward = true;
                    }

                    @Override
                    public void onRewardedVideoAdShowFailed(IronSourceError error) {
                    }

                    @Override
                    public void onRewardedVideoAdClicked(Placement placement) {
                    }

                    @Override
                    public void onRewardedVideoAdStarted() {
                    }

                    @Override
                    public void onRewardedVideoAdEnded() {
                    }
                });
                break;

        }
    }

    public static void LoadRewardApplovinDis(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idReward, AppLovinSdk.getInstance(activity));
        incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd appLovinAd) {
                // A rewarded video was successfully received.
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                // A rewarded video failed to load.
            }
        });
        switch (selectBackupAds) {
            case "ADMOB":
                AdRequest adRequest = new AdRequest.Builder().build();
                RewardedAd.load(activity, idBackupReward,
                        adRequest, new RewardedAdLoadCallback() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                mRewardedAd = null;
                            }

                            @Override
                            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                mRewardedAd = rewardedAd;

                            }
                        });

                break;
            case "APPLOVIN-M":
                rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                rewardedAd.loadAd();
                MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                    @Override
                    public void onRewardedVideoStarted(MaxAd ad) {

                    }

                    @Override
                    public void onRewardedVideoCompleted(MaxAd ad) {
                        unlockreward = true;
                    }

                    @Override
                    public void onUserRewarded(MaxAd ad, MaxReward reward) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {

                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {

                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {

                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    }
                };
                rewardedAd.setListener(maxRewardedAdListener);

                break;
            case "IRON":
                IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                    @Override
                    public void onRewardedVideoAdOpened() {
                    }

                    @Override
                    public void onRewardedVideoAdClosed() {
                    }

                    @Override
                    public void onRewardedVideoAvailabilityChanged(boolean available) {
                    }

                    @Override
                    public void onRewardedVideoAdRewarded(Placement placement) {
                        unlockreward = true;
                    }

                    @Override
                    public void onRewardedVideoAdShowFailed(IronSourceError error) {
                    }

                    @Override
                    public void onRewardedVideoAdClicked(Placement placement) {
                    }

                    @Override
                    public void onRewardedVideoAdStarted() {
                    }

                    @Override
                    public void onRewardedVideoAdEnded() {
                    }
                });
                break;

        }
    }

    public static void ShowReward(Activity activity, String selectAds, String idReward) {
        switch (selectAds) {
            case "ADMOB":
                if (mRewardedAd != null) {
                    Activity activityContext = activity;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            unlockreward = true;
                            LoadReward(activity, selectAds, idReward);
                        }
                    });
                } else {
                    LoadReward(activity, selectAds, idReward);
                }
                break;
            case "APPLOVIN-M":
                if (rewardedAd.isReady()) {
                    rewardedAd.showAd();
                    LoadReward(activity, selectAds, idReward);
                } else {
                    LoadReward(activity, selectAds, idReward);
                }
                break;

            case "APPLOVIN-D":
                if(incentivizedInterstitial != null) {
                    // A rewarded video is available.  Call the show method with the listeners you want to use.
                    // We will use the display listener to preload the next rewarded video when this one finishes.
                    incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                        @Override
                        public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                            unlockreward = true;
                        }

                        @Override
                        public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                        }

                        @Override
                        public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                        }

                        @Override
                        public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                        }

                        @Override
                        public void userDeclinedToViewAd(AppLovinAd ad) {

                        }
                    }, null, new AppLovinAdDisplayListener() {
                        @Override
                        public void adDisplayed(AppLovinAd appLovinAd) {
                            // A rewarded video is being displayed.
                        }

                        @Override
                        public void adHidden(AppLovinAd appLovinAd) {
                            // A rewarded video was closed.  Preload the next video now.  We won't use a load listener.
                            incentivizedInterstitial.preload(null);
                        }
                    });
                }
                break;

        }

    }

    public static void ShowRewardAdmob(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        if (mRewardedAd != null) {
            Activity activityContext = activity;
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    unlockreward = true;
                    LoadRewardAdmob(activity, selectBackupAds, idReward, idBackupReward);
                }
            });
        } else {
            LoadRewardAdmob(activity, selectBackupAds, idReward, idBackupReward);
            switch (selectBackupAds) {
                case "APPLOVIN-M":
                    if (rewardedAd.isReady()) {
                        rewardedAd.showAd();
                    }
                    break;

                case "APPLOVIN-D":
                    if(incentivizedInterstitial != null){
                        // A rewarded video is available.  Call the show method with the listeners you want to use.
                        // We will use the display listener to preload the next rewarded video when this one finishes.
                        incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                            @Override
                            public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                unlockreward = true;
                            }

                            @Override
                            public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                            }

                            @Override
                            public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                            }

                            @Override
                            public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                            }

                            @Override
                            public void userDeclinedToViewAd(AppLovinAd ad) {

                            }
                        }, null, new AppLovinAdDisplayListener() {
                            @Override
                            public void adDisplayed(AppLovinAd appLovinAd) {
                                // A rewarded video is being displayed.
                            }

                            @Override
                            public void adHidden(AppLovinAd appLovinAd) {
                                // A rewarded video was closed.  Preload the next video now.  We won't use a load listener.
                                incentivizedInterstitial.preload(null);
                            }
                        });
                    }
                    break;
            }
        }
    }

    public static void ShowRewardApplovinMax(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

        if (rewardedAd.isReady()) {
            rewardedAd.showAd();
            LoadRewardApplovinMax(activity, selectBackupAds, idReward, idBackupReward);
        } else {
            LoadRewardApplovinMax(activity, selectBackupAds, idReward, idBackupReward);
            switch (selectBackupAds) {
                case "ADMOB":
                    if (mRewardedAd != null) {
                        Activity activityContext = activity;
                        mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                unlockreward = true;
                            }
                        });
                    }
                    break;

                case "APPLOVIN-D":
                    if(incentivizedInterstitial != null){
                        // A rewarded video is available.  Call the show method with the listeners you want to use.
                        // We will use the display listener to preload the next rewarded video when this one finishes.
                        incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                            @Override
                            public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                unlockreward = true;
                            }

                            @Override
                            public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                            }

                            @Override
                            public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                            }

                            @Override
                            public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                            }

                            @Override
                            public void userDeclinedToViewAd(AppLovinAd ad) {

                            }
                        }, null, new AppLovinAdDisplayListener() {
                            @Override
                            public void adDisplayed(AppLovinAd appLovinAd) {
                                // A rewarded video is being displayed.
                            }

                            @Override
                            public void adHidden(AppLovinAd appLovinAd) {
                                // A rewarded video was closed.  Preload the next video now.  We won't use a load listener.
                                incentivizedInterstitial.preload(null);
                            }
                        });
                    }
                    break;
            }
        }
    }

    public static void ShowRewardApplovinDis(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        if (incentivizedInterstitial != null) {
            // A rewarded video is available.  Call the show method with the listeners you want to use.
            // We will use the display listener to preload the next rewarded video when this one finishes.
            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                @Override
                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                    unlockreward = true;
                }

                @Override
                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                }

                @Override
                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                }

                @Override
                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                }

                @Override
                public void userDeclinedToViewAd(AppLovinAd ad) {

                }
            }, null, new AppLovinAdDisplayListener() {
                @Override
                public void adDisplayed(AppLovinAd appLovinAd) {
                    // A rewarded video is being displayed.
                }

                @Override
                public void adHidden(AppLovinAd appLovinAd) {
                    // A rewarded video was closed.  Preload the next video now.  We won't use a load listener.
                    incentivizedInterstitial.preload(null);
                }
            });
            LoadRewardApplovinDis(activity, selectBackupAds, idReward, idBackupReward);
        } else {
            LoadRewardApplovinDis(activity, selectBackupAds, idReward, idBackupReward);
            switch (selectBackupAds) {
                case "ADMOB":
                    if (mRewardedAd != null) {
                        Activity activityContext = activity;
                        mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                unlockreward = true;
                            }
                        });
                    }
                    break;

                case "APPLOVIN-M":
                    if (rewardedAd.isReady()) {
                        rewardedAd.showAd();
                    }
                    break;
            }
        }
    }

}
