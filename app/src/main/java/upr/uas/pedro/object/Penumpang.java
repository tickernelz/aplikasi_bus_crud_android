package upr.uas.pedro.object;

import android.os.Parcel;
import android.os.Parcelable;

public class Penumpang implements Parcelable {

  public static final Creator<Penumpang> CREATOR =
      new Creator<Penumpang>() {
        @Override
        public Penumpang createFromParcel(Parcel in) {
          return new Penumpang(in);
        }

        @Override
        public Penumpang[] newArray(int size) {
          return new Penumpang[size];
        }
      };
  private int id;
  private String nama, umur, kelamin;

  public Penumpang(int id, String nama, String umur, String kelamin) {
    this.id = id;
    this.nama = nama;
    this.umur = umur;
    this.kelamin = kelamin;
  }

  protected Penumpang(Parcel in) {
    id = in.readInt();
    nama = in.readString();
    umur = in.readString();
    kelamin = in.readString();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getUmur() {
    return umur;
  }

  public void setUmur(String umur) {
    this.umur = umur;
  }

  public String getKelamin() {
    return kelamin;
  }

  public void setKelamin(String kelamin) {
    this.kelamin = kelamin;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(nama);
    parcel.writeString(umur);
    parcel.writeString(kelamin);
  }
}
