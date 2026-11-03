package com.bytedance.sdk.open.tiktok.share;

import com.bytedance.sdk.open.tiktok.base.ImageObject;
import com.bytedance.sdk.open.tiktok.base.MediaContent;
import com.bytedance.sdk.open.tiktok.base.VideoObject;
import com.bytedance.sdk.open.tiktok.share.Share;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes3.dex */
public class ShareRequest {
    private Share.Request shareRequest;

    /* loaded from: classes3.dex */
    public enum MediaType {
        IMAGE,
        VIDEO
    }

    /* synthetic */ ShareRequest(Share.Request request, AnonymousClass1 anonymousClass1) {
        this(request);
    }

    private ShareRequest(Share.Request request) {
        this.shareRequest = request;
    }

    public Share.Request getShareRequest() {
        return this.shareRequest;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private String anchorSourceType;
        private String extra;
        private final HashMap<String, Integer> extraShareOptions;
        private List<String> hashtags;
        private List<String> mediaPaths;
        private MediaType mediaType;

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            this.extraShareOptions = new HashMap<>();
        }

        public Builder mediaPaths(List<String> list) {
            this.mediaPaths = list;
            return this;
        }

        public Builder mediaType(MediaType mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public Builder hashtags(List<String> list) {
            this.hashtags = list;
            return this;
        }

        public Builder extra(String str) {
            this.extra = str;
            return this;
        }

        public Builder anchorSourceType(String str) {
            this.anchorSourceType = str;
            return this;
        }

        public Builder putExtraShareOptions(String str, Integer num) {
            this.extraShareOptions.put(str, num);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ShareRequest build() {
            ImageObject imageObject;
            if (this.mediaType == null) {
                throw new IllegalStateException("Share request must specify media type");
            }
            if (this.mediaPaths == null) {
                throw new IllegalStateException("Share request must specify media paths");
            }
            Share.Request request = new Share.Request();
            int i2 = AnonymousClass1.$SwitchMap$com$bytedance$sdk$open$tiktok$share$ShareRequest$MediaType[this.mediaType.ordinal()];
            if (i2 == 1) {
                ImageObject imageObject2 = new ImageObject();
                imageObject2.mImagePaths = new ArrayList<>(this.mediaPaths);
                imageObject = imageObject2;
            } else if (i2 == 2) {
                VideoObject videoObject = new VideoObject();
                videoObject.mVideoPaths = new ArrayList<>(this.mediaPaths);
                imageObject = videoObject;
            } else {
                throw new IllegalStateException("Unsupported media type");
            }
            MediaContent mediaContent = new MediaContent();
            mediaContent.mMediaObject = imageObject;
            request.mMediaContent = mediaContent;
            request.mExtra = this.extra;
            request.mAnchorSourceType = this.anchorSourceType;
            request.extraShareOptions = this.extraShareOptions;
            if (this.hashtags != null) {
                request.mHashTagList = new ArrayList<>(this.hashtags);
            }
            return new ShareRequest(request, null);
        }
    }

    /* renamed from: com.bytedance.sdk.open.tiktok.share.ShareRequest$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$bytedance$sdk$open$tiktok$share$ShareRequest$MediaType;

        static {
            int[] iArr = new int[MediaType.values().length];
            $SwitchMap$com$bytedance$sdk$open$tiktok$share$ShareRequest$MediaType = iArr;
            try {
                iArr[MediaType.IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bytedance$sdk$open$tiktok$share$ShareRequest$MediaType[MediaType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
