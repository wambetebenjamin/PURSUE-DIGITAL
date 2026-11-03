package com.google.zxing.maxicode.decoder;

import com.bytedance.apm.battery.config.a;
import com.bytedance.common.wschannel.channel.impl.ok.f;
import com.bytedance.frameworks.baselib.network.http.util.f;
import com.bytedance.geckox.b;
import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.ttgame.gsdksync.b;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.facebook.imageutils.JfifUtil;
import com.facebook.imageutils.TiffUtil;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.zxing.common.BitMatrix;
import com.just.agentweb.AgentActionFragment;
import com.oasis.immortal.BuildConfig;
import com.twitter.sdk.android.core.internal.TwitterApiConstants;
import com.vk.api.sdk.exceptions.VKApiCodes;
/* loaded from: classes9.dex */
final class BitMatrixParser {
    private static final int[][] BITNR = {new int[]{121, 120, 127, 126, 133, 132, TwitterApiConstants.Errors.ALREADY_FAVORITED, 138, 145, TwitterApiConstants.Errors.ALREADY_UNFAVORITED, 151, VKApiCodes.CODE_INVALID_TIMESTAMP, 157, 156, 163, 162, 169, 168, 175, 174, 181, RotationOptions.ROTATE_180, 187, 186, 193, JfifUtil.MARKER_SOFn, 199, 198, -2, -2}, new int[]{123, 122, 129, 128, 135, 134, 141, 140, 147, 146, 153, 152, 159, 158, 165, 164, b.f14143c, 170, 177, 176, 183, 182, 189, 188, 195, 194, 201, 200, 816, -3}, new int[]{125, 124, 131, 130, 137, 136, 143, 142, 149, 148, 155, 154, 161, 160, 167, 166, 173, 172, 179, 178, 185, 184, 191, 190, 197, 196, 203, 202, 818, 817}, new int[]{283, 282, 277, 276, 271, RotationOptions.ROTATE_270, 265, 264, 259, 258, VKApiCodes.CODE_ACCESS_POLLS_WITHOUT_VOTE, 252, 247, 246, 241, a.MAX_TOTAL_LOC_REQUEST_TIME_10_MINS_SECOND, 235, 234, 229, 228, 223, 222, 217, 216, 211, 210, 205, 204, 819, -3}, new int[]{285, 284, 279, 278, 273, 272, 267, 266, 261, 260, 255, 254, 249, 248, 243, 242, 237, 236, 231, 230, JfifUtil.MARKER_APP1, 224, e.c.t, 218, 213, 212, 207, 206, 821, 820}, new int[]{287, 286, 281, 280, 275, TiffUtil.TIFF_TAG_ORIENTATION, 269, 268, 263, BuildConfig.VERSION_CODE, 257, 256, 251, 250, 245, net.openid.appauth.BuildConfig.VERSION_CODE, TwitterApiConstants.Errors.GUEST_AUTH_ERROR_CODE, 238, 233, 232, 227, 226, 221, 220, JfifUtil.MARKER_RST7, 214, 209, 208, 822, -3}, new int[]{289, 288, 295, 294, 301, 300, 307, 306, 313, 312, 319, 318, 325, 324, 331, 330, 337, 336, 343, 342, 349, 348, 355, 354, 361, 360, 367, 366, 824, 823}, new int[]{291, 290, 297, 296, 303, 302, 309, 308, 315, 314, 321, 320, 327, 326, 333, 332, 339, 338, 345, 344, 351, 350, 357, 356, 363, 362, 369, 368, 825, -3}, new int[]{293, 292, 299, 298, f.V, 304, 311, 310, 317, 316, 323, 322, 329, 328, 335, 334, FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS, 340, 347, 346, 353, 352, 359, 358, 365, 364, 371, 370, 827, 826}, new int[]{409, f.K, 403, 402, 397, 396, 391, 390, 79, 78, -2, -2, 13, 12, 37, 36, 2, -1, 44, 43, 109, 108, 385, BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, 379, 378, 373, 372, 828, -3}, new int[]{411, 410, f.s, 404, 399, 398, 393, 392, 81, 80, 40, -2, 15, 14, 39, 38, 3, -1, -1, 45, 111, 110, 387, 386, 381, 380, 375, 374, 830, 829}, new int[]{413, 412, f.I, f.y, 401, 400, 395, 394, 83, 82, 41, -3, -3, -3, -3, -3, 5, 4, 47, 46, 113, 112, 389, 388, 383, 382, 377, 376, 831, -3}, new int[]{415, 414, 421, f.r, 427, 426, 103, 102, 55, 54, 16, -3, -3, -3, -3, -3, -3, -3, 20, 19, 85, 84, 433, 432, 439, 438, 445, 444, 833, 832}, new int[]{417, 416, f.q, f.T, 429, 428, 105, 104, 57, 56, -3, -3, -3, -3, -3, -3, -3, -3, 22, 21, 87, 86, 435, 434, 441, 440, 447, 446, 834, -3}, new int[]{f.m, 418, 425, f.f8334h, 431, 430, 107, 106, 59, 58, -3, -3, -3, -3, -3, -3, -3, -3, -3, 23, 89, 88, 437, 436, 443, 442, 449, 448, 836, 835}, new int[]{481, 480, 475, 474, 469, 468, 48, -2, 30, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 0, 53, 52, 463, 462, 457, 456, 451, 450, 837, -3}, new int[]{483, 482, 477, 476, 471, 470, 49, -1, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -1, 465, 464, 459, FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED, 453, 452, 839, 838}, new int[]{485, 484, 479, 478, 473, 472, 51, 50, 31, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 1, -2, 42, 467, 466, 461, 460, 455, 454, 840, -3}, new int[]{487, 486, FacebookRequestErrorClassification.ESC_APP_INACTIVE, 492, 499, 498, 97, 96, 61, 60, -3, -3, -3, -3, -3, -3, -3, -3, -3, 26, 91, 90, f.l, f.f8336j, 511, f.b.l, 517, 516, 842, 841}, new int[]{489, 488, 495, 494, 501, 500, 99, 98, 63, 62, -3, -3, -3, -3, -3, -3, -3, -3, 28, 27, 93, 92, com.bytedance.frameworks.baselib.network.http.util.f.n, 506, 513, 512, 519, 518, 843, -3}, new int[]{491, 490, 497, 496, com.bytedance.frameworks.baselib.network.http.util.f.P, com.bytedance.frameworks.baselib.network.http.util.f.f8328b, 101, 100, 65, 64, 17, -3, -3, -3, -3, -3, -3, -3, 18, 29, 95, 94, 509, 508, 515, f.b.p, 521, 520, 845, 844}, new int[]{559, 558, 553, 552, 547, 546, 541, 540, 73, 72, 32, -3, -3, -3, -3, -3, -3, 10, 67, 66, 115, 114, 535, 534, 529, 528, 523, 522, 846, -3}, new int[]{561, 560, 555, 554, 549, 548, 543, 542, 75, 74, -2, -1, 7, 6, 35, 34, 11, -2, 69, 68, 117, 116, 537, 536, 531, 530, 525, 524, 848, 847}, new int[]{563, 562, 557, 556, 551, 550, 545, 544, 77, 76, -2, 33, 9, 8, 25, 24, -1, -2, 71, 70, 119, 118, 539, 538, 533, 532, 527, 526, 849, -3}, new int[]{565, 564, 571, 570, 577, 576, 583, 582, 589, 588, 595, 594, b.InterfaceC0127b.f8891b, 600, 607, 606, 613, 612, 619, 618, 625, 624, 631, 630, 637, 636, 643, 642, 851, 850}, new int[]{567, 566, 573, 572, 579, 578, 585, 584, 591, 590, 597, AgentActionFragment.REQUEST_CODE, VKApiCodes.CODE_ADVERTISE_CABINET_ERROR, 602, 609, 608, 615, 614, 621, 620, 627, 626, 633, 632, 639, 638, 645, 644, 852, -3}, new int[]{569, 568, 575, 574, 581, 580, 587, 586, 593, 592, 599, 598, 605, 604, 611, 610, 617, 616, 623, 622, 629, 628, 635, 634, 641, 640, 647, 646, 854, 853}, new int[]{727, 726, 721, 720, 715, 714, 709, 708, 703, 702, 697, 696, 691, 690, 685, 684, 679, 678, 673, 672, 667, 666, 661, 660, 655, 654, 649, 648, 855, -3}, new int[]{729, 728, 723, 722, 717, 716, 711, 710, 705, 704, 699, 698, 693, 692, 687, 686, 681, 680, 675, 674, 669, 668, 663, 662, 657, 656, 651, 650, 857, 856}, new int[]{731, 730, 725, 724, 719, 718, 713, 712, 707, 706, 701, 700, 695, 694, 689, 688, 683, 682, 677, 676, 671, 670, 665, 664, 659, 658, 653, 652, 858, -3}, new int[]{733, 732, 739, 738, 745, 744, 751, 750, 757, 756, 763, 762, 769, 768, 775, 774, 781, 780, 787, 786, 793, 792, 799, 798, 805, 804, 811, 810, 860, 859}, new int[]{735, 734, 741, 740, 747, 746, 753, 752, 759, 758, 765, 764, 771, 770, 777, 776, 783, 782, 789, 788, 795, 794, VKApiCodes.CODE_ERROR_VIDEO_COMMENTS_CLOSED, 800, 807, 806, 813, 812, 861, -3}, new int[]{737, 736, 743, 742, 749, 748, 755, 754, 761, 760, 767, 766, 773, 772, 779, 778, 785, 784, 791, 790, 797, 796, 803, 802, 809, 808, 815, 814, 863, 862}};
    private final BitMatrix bitMatrix;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitMatrixParser(BitMatrix bitMatrix) {
        this.bitMatrix = bitMatrix;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] readCodewords() {
        byte[] bArr = new byte[TwitterApiConstants.Errors.ALREADY_UNFAVORITED];
        int height = this.bitMatrix.getHeight();
        int width = this.bitMatrix.getWidth();
        for (int i2 = 0; i2 < height; i2++) {
            int[] iArr = BITNR[i2];
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = iArr[i3];
                if (i4 >= 0 && this.bitMatrix.get(i3, i2)) {
                    int i5 = i4 / 6;
                    bArr[i5] = (byte) (((byte) (1 << (5 - (i4 % 6)))) | bArr[i5]);
                }
            }
        }
        return bArr;
    }
}
