package com.example.serviceku.UnitTesting;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfilPresenterTest {
    @Mock
    private ProfilView view;

    @Mock
    private ProfilService service;
    private ProfilPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ProfilPresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        when(view.getEmail()).thenReturn("");
        System.out.println("Testing Pertama: Inputan Email Kosong");
        System.out.println("Email : " + view.getEmail());
        presenter.onProfilClicked();
        verify(view).showEmailError("Email tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        System.out.println("Testing Kedua: Inputan Password Kosong");

        when(view.getEmail()).thenReturn("gesha0109@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("");
        System.out.println("Password : " + view.getPassword());

        presenter.onProfilClicked();
        verify(view).showPasswordError("Password tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenNamaIsEmpty() throws Exception {
        System.out.println("Testing Ketiga: Inputan Name Kosong");

        when(view.getEmail()).thenReturn("gesha0109@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("123");
        System.out.println("Password : " + view.getPassword());

        when(view.getNama()).thenReturn("");
        System.out.println("Name : " + view.getNama());

        presenter.onProfilClicked();
        verify(view).showNamaError("Nama tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPhoneNoIsEmpty() throws Exception {
        System.out.println("Testing Keempat: Inputan PhoneNo Kosong");

        when(view.getEmail()).thenReturn("gesha0109@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("123");
        System.out.println("Password : " + view.getPassword());

        when(view.getNama()).thenReturn("Gesha0109");
        System.out.println("Name : " + view.getNama());

        when(view.getPhoneNo()).thenReturn("");
        System.out.println("PhoneNo : " + view.getPhoneNo());

        presenter.onProfilClicked();
        verify(view).showPhoneNoError("Nomor handphone tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenGenderIsEmpty() throws Exception {
        System.out.println("Testing Kelima: Inputan Gender Kosong");

        when(view.getEmail()).thenReturn("gesha0109@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("123");
        System.out.println("Password : " + view.getPassword());

        when(view.getNama()).thenReturn("Gesha0109");
        System.out.println("Name : " + view.getNama());

        when(view.getPhoneNo()).thenReturn("0812190710109");
        System.out.println("PhoneNo : " + view.getPhoneNo());

        when(view.getGender()).thenReturn("Gender kosong");
        System.out.println("Gender : " + view.getGender());

        presenter.onProfilClicked();
        verify(view).showGenderError("Jenis kelamin tidak boleh kosong");
    }
}