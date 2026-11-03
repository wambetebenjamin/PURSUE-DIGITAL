package com.google.zxing.pdf417.encoder;

import com.bytedance.apm.battery.config.a;
import com.bytedance.frameworks.baselib.network.http.util.f;
import com.bytedance.geckox.b;
import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.ttgame.gsdksync.b;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.facebook.imageutils.JfifUtil;
import com.facebook.imageutils.TiffUtil;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import com.just.agentweb.AgentActionFragment;
import com.twitter.sdk.android.core.internal.TwitterApiConstants;
import com.vk.api.sdk.exceptions.VKApiCodes;
import net.openid.appauth.BuildConfig;
/* loaded from: classes9.dex */
final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = {new int[]{27, 917}, new int[]{522, 568, 723, 809}, new int[]{237, 308, 436, 284, 646, 653, 428, 379}, new int[]{TiffUtil.TIFF_TAG_ORIENTATION, 562, 232, 755, 599, 524, VKApiCodes.CODE_ERROR_VIDEO_COMMENTS_CLOSED, 132, 295, 116, 442, 428, 295, 42, 176, 65}, new int[]{361, 575, 922, 525, 176, 586, 640, 321, 536, 742, 677, 742, 687, 284, 193, 517, 273, 494, 263, 147, 593, 800, 571, 320, 803, 133, 231, 390, 685, 330, 63, 410}, new int[]{539, f.T, 6, 93, 862, 771, 453, 106, 610, 287, 107, f.l, 733, 877, 381, 612, 723, 476, 462, 172, 430, 609, 858, 822, 543, 376, 511, 400, 672, 762, 283, 184, 440, 35, 519, 31, 460, 594, JfifUtil.MARKER_APP1, 535, 517, 352, 605, 158, 651, 201, 488, f.f8328b, 648, 733, 717, 83, 404, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, 400, VKApiCodes.CODE_CHAT_NOT_ADMIN, 749, 415, 822, 93, 217, 208, PDF417Common.MAX_CODEWORDS_IN_BARCODE, BuildConfig.VERSION_CODE, 583, 620, 246, 148, 447, 631, 292, VKApiCodes.CODE_CLEAR_CACHE_REQUESTED2, 490, 704, 516, 258, 457, VKApiCodes.CODE_CLEAR_CACHE_REQUESTED, 594, 723, 674, 292, 272, 96, 684, 432, 686, 606, 860, 569, 193, e.c.t, 129, 186, 236, 287, JfifUtil.MARKER_SOFn, 775, 278, 173, 40, 379, 712, 463, 646, 776, b.f14143c, 491, 297, 763, 156, 732, 95, RotationOptions.ROTATE_270, 447, 90, f.n, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, com.oasis.immortal.BuildConfig.VERSION_CODE, 380, 602, 754, 336, 89, 614, 87, 432, 670, 616, 157, 374, 242, 726, 600, 269, 375, 898, 845, 454, 354, 130, 814, 587, 804, 34, 211, 330, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, VKApiCodes.CODE_ERROR_VIDEO_COMMENTS_CLOSED, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, 204, 82, 586, 708, 250, 905, 786, 138, 720, 858, 194, 311, VKApiCodes.CODE_MSG_SEND_TO_MANY_FWDS, 275, 190, 375, 850, 438, 733, 194, 280, 201, 280, 828, 757, 710, 814, VKApiCodes.CODE_CHAT_INVITE_MAKE_LINK_DENIED, 89, 68, 569, 11, 204, 796, 605, 540, VKApiCodes.CODE_MSG_SEND_TO_MANY_FWDS, VKApiCodes.CODE_ERROR_VIDEO_COMMENTS_CLOSED, 700, 799, 137, 439, 418, 592, 668, 353, 859, 370, 694, 325, a.MAX_TOTAL_LOC_REQUEST_TIME_10_MINS_SECOND, 216, 257, 284, 549, 209, 884, 315, 70, 329, 793, 490, TiffUtil.TIFF_TAG_ORIENTATION, 877, 162, 749, 812, 684, 461, 334, 376, 849, 521, 307, 291, 803, 712, 19, 358, 399, VKApiCodes.CODE_CLEAR_CACHE_REQUESTED2, 103, 511, 51, 8, 517, JfifUtil.MARKER_APP1, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, 433, 848, 585, 136, 538, 906, 90, 2, 290, 743, 199, 655, 903, 329, 49, 802, 580, 355, 588, 188, 462, 10, 134, 628, 320, 479, 130, 739, 71, 263, 318, 374, b.InterfaceC0127b.f8891b, JfifUtil.MARKER_SOFn, 605, 142, 673, 687, 234, 722, BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, 177, 752, 607, 640, 455, 193, 689, 707, 805, 641, 48, 60, 732, 621, 895, 544, 261, 852, 655, 309, 697, 755, 756, 60, 231, 773, 434, 421, 726, 528, f.P, 118, 49, 795, 32, TwitterApiConstants.Errors.ALREADY_UNFAVORITED, 500, 238, 836, 394, 280, 566, 319, 9, 647, 550, 73, VKApiCodes.CODE_MSG_SEND_MSG_TOO_LONG, 342, 126, 32, 681, 331, 792, 620, 60, 609, 441, RotationOptions.ROTATE_180, 791, 893, 754, 605, 383, 228, 749, 760, 213, 54, 297, 134, 54, 834, 299, 922, 191, 910, 532, 609, 829, 189, 20, 167, 29, 872, 449, 83, 402, 41, 656, f.l, 579, 481, 173, 404, 251, 688, 95, 497, 555, 642, 543, 307, 159, VKApiCodes.CODE_MSG_DELETE_FOR_ALL_FAILED, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, f.f8336j, 35, 599, 428, 207, 409, 574, 118, 498, 285, 380, 350, 492, 197, 265, 920, 155, VKApiCodes.CODE_MSG_SEND_MSG_TOO_LONG, 299, 229, 643, 294, 871, 306, 88, 87, 193, 352, 781, 846, 75, 327, 520, 435, 543, 203, 666, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, f.K, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, 122, 272, 383, 800, 485, 98, 752, 472, 761, 107, 784, 860, 658, 741, 290, 204, 681, f.I, 855, 85, 99, 62, 482, RotationOptions.ROTATE_180, 20, 297, 451, 593, VKApiCodes.CODE_MSG_SEND_TO_MANY_FWDS, 142, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, 513, JfifUtil.MARKER_SOFn, 516, 258, a.MAX_TOTAL_LOC_REQUEST_TIME_10_MINS_SECOND, 518, 794, 395, 768, 848, 51, 610, BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, 168, 190, 826, 328, AgentActionFragment.REQUEST_CODE, 786, 303, 570, 381, 415, 641, 156, 237, 151, 429, 531, 207, 676, 710, 89, 168, 304, 402, 40, 708, 575, 162, 864, 229, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, 707, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, 248, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, VKApiCodes.CODE_MSG_SEND_VIOLATION_OF_PRIVACY_SETTINGS, 452, 167, 342, BuildConfig.VERSION_CODE, 173, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, f.r, 245, 288, 594, 394, 511, 327, 589, 777, 699, 688, 43, f.K, 842, 383, 721, 521, 560, 644, 714, 559, 62, 145, 873, 663, 713, 159, 672, 729, 624, 59, 193, 417, 158, 209, 563, 564, 343, 693, 109, 608, 563, 365, 181, 772, 677, 310, 248, 353, 708, 410, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, f.f8334h, 833, 77, 597, 346, 269, 757, 632, 695, 751, 331, 247, 184, 45, 787, 680, 18, 66, f.I, 369, 54, 492, 228, 613, 830, 922, 437, 519, 644, 905, 789, f.r, f.V, 441, 207, 300, 892, 827, 141, 537, 381, 662, 513, 56, 252, FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS, 242, 797, 838, 837, 720, 224, 307, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, 425, 216, 548, 249, 321, 881, 699, 535, 673, 782, 210, 815, 905, 303, 843, 922, 281, 73, 469, 791, 660, 162, 498, 308, 155, f.T, VKApiCodes.CODE_CLEAR_CACHE_REQUESTED, 817, 187, 62, 16, 425, 535, 336, 286, 437, 375, 273, 610, 296, 183, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, 330, 5, 39, 923, 311, f.f8334h, 242, 749, 321, 54, 669, 316, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, 316, 486, 721, 610, 46, 656, 447, com.bytedance.ttgame.gsdksync.b.f14143c, 616, 464, 190, 531, 297, 321, 762, 752, 533, 175, 134, 14, 381, 433, 717, 45, 111, 20, AgentActionFragment.REQUEST_CODE, 284, 736, 138, 646, 411, 877, 669, 141, VKApiCodes.CODE_CHAT_INVITE_MAKE_LINK_DENIED, 45, 780, f.I, 164, 332, 899, 165, 726, 600, 325, 498, 655, 357, 752, 768, 223, 849, 647, 63, 310, 863, 251, 366, 304, 282, 738, 675, 410, 389, BuildConfig.VERSION_CODE, 31, 121, 303, 263}};

    private PDF417ErrorCorrection() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getErrorCorrectionCodewordCount(int i2) {
        if (i2 < 0 || i2 > 8) {
            throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
        }
        return 1 << (i2 + 1);
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int i2) throws WriterException {
        if (i2 > 0) {
            if (i2 <= 40) {
                return 2;
            }
            if (i2 <= 160) {
                return 3;
            }
            if (i2 <= 320) {
                return 4;
            }
            if (i2 <= 863) {
                return 5;
            }
            throw new WriterException("No recommendation possible");
        }
        throw new IllegalArgumentException("n must be > 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String generateErrorCorrection(CharSequence charSequence, int i2) {
        int errorCorrectionCodewordCount = getErrorCorrectionCodewordCount(i2);
        char[] cArr = new char[errorCorrectionCodewordCount];
        int length = charSequence.length();
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = errorCorrectionCodewordCount - 1;
            int charAt = (charSequence.charAt(i3) + cArr[i4]) % PDF417Common.NUMBER_OF_CODEWORDS;
            while (i4 > 0) {
                cArr[i4] = (char) ((cArr[i4 - 1] + (929 - ((EC_COEFFICIENTS[i2][i4] * charAt) % PDF417Common.NUMBER_OF_CODEWORDS))) % PDF417Common.NUMBER_OF_CODEWORDS);
                i4--;
            }
            cArr[0] = (char) ((929 - ((charAt * EC_COEFFICIENTS[i2][0]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder sb = new StringBuilder(errorCorrectionCodewordCount);
        for (int i5 = errorCorrectionCodewordCount - 1; i5 >= 0; i5--) {
            if (cArr[i5] != 0) {
                cArr[i5] = (char) (929 - cArr[i5]);
            }
            sb.append(cArr[i5]);
        }
        return sb.toString();
    }
}
