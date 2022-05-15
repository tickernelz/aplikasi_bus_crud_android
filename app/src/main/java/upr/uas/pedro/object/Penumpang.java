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
  private String nama, kode, tipe, rute;

  public Penumpang(int id, String nama, String kode, String tipe, String rute) {
    this.id = id;
    this.nama = nama;
    this.kode = kode;
    this.tipe = tipe;
    this.rute = rute;
  }

  protected Penumpang(Parcel in) {
    id = in.readInt();
    nama = in.readString();
    kode = in.readString();
    tipe = in.readString();
    rute = in.readString();
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

  public String getKode() {
    return kode;
  }

  public void setKode(String kode) {
    this.kode = kode;
  }

  public String getTipe() {
    return tipe;
  }

  public void setTipe(String tipe) {
    this.tipe = tipe;
  }

  public String getRute() {
    return rute;
  }

  public void setRute(String rute) {
    this.rute = rute;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(nama);
    parcel.writeString(kode);
    parcel.writeString(tipe);
    parcel.writeString(rute);
  }
}
