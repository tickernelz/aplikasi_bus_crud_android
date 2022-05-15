package upr.uas.pedro.object;

import android.os.Parcel;
import android.os.Parcelable;

public class Pemesanan implements Parcelable {

  public static final Creator<Pemesanan> CREATOR =
      new Creator<Pemesanan>() {
        @Override
        public Pemesanan createFromParcel(Parcel in) {
          return new Pemesanan(in);
        }

        @Override
        public Pemesanan[] newArray(int size) {
          return new Pemesanan[size];
        }
      };
  private int id;
  private String nama, kode, jadwal;

  public Pemesanan(int id, String nama, String kode, String jadwal) {
    this.id = id;
    this.nama = nama;
    this.kode = kode;
    this.jadwal = jadwal;
  }

  protected Pemesanan(Parcel in) {
    id = in.readInt();
    nama = in.readString();
    kode = in.readString();
    jadwal = in.readString();
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

  public String getJadwal() {
    return jadwal;
  }

  public void setJadwal(String jadwal) {
    this.jadwal = jadwal;
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
    parcel.writeString(jadwal);
  }
}
