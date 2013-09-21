package profile

import org.bson.types.ObjectId;

class FacebookUser {

    ObjectId id
    long uid
    String accessToken
    Date accessTokenExpires

    static belongsTo = [user: User]

    static constraints = { uid unique: true }
}
