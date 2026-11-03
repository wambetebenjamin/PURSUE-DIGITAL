package f;

import com.oasis.Logger.Logger;
import com.oasis.relationship.RelationshipAgent;
import com.oasis.relationship.RelationshipListener;
/* loaded from: classes10.dex */
public class a extends RelationshipAgent {
    @Override // com.oasis.relationship.RelationshipAgent
    public void getFriendChainInfo(int i2, boolean z, RelationshipListener relationshipListener) {
        if (relationshipListener != null) {
            relationshipListener.OnFetched(false, "");
        }
    }

    @Override // com.oasis.relationship.RelationshipAgent
    public void hasOpenFriendChainPermission(int i2, RelationshipListener relationshipListener) {
        Logger.i("DefaultRelationship", "hasOpenFriendChainPermission");
        if (relationshipListener != null) {
            relationshipListener.OnFetched(false, "not support!");
        }
    }
}
