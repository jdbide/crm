package fr.datour.protoclientrest.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import fr.datour.protoclientrest.model.orm.ProtoClientRESTDatabase;

@Table(databaseName = ProtoClientRESTDatabase.NAME)
public class User extends BaseModel implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @Column
    @PrimaryKey
    private Integer id;
    @Column
    private String  prenom;
    @Column
    private String  nom;

    public User() {
    }

    protected User(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.prenom = in.readString();
        this.nom = in.readString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.prenom);
        dest.writeString(this.nom);
    }
}
