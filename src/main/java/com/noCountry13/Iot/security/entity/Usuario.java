package com.noCountry13.Iot.security.entity;

import com.noCountry13.Iot.Model.Entity.House;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Usuario {

		//Id de la tabla
		@Id
		//Id Auto Increment
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int idUsuario;
		//Decorador para indicar que no puede ser null el campo
		@NotNull
		private String nombre;
		@NotNull
		@Column(unique = true)
		private String nombreUsuario;
		@NotNull
		@Column(unique = true)
		private String email;
		@NotNull
		private String password;
		private String topic;
		@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
		@JoinColumn(name = "client_id")
		private List<House> houses;

		@NotNull
		//Relación many to many
		//Un usuario puede tener MUCHOS roles y un rol puede PERTENECER a varios usuarios
		//Tabla intermedia que tiene dos campos que va a tener idUsuario y idRol
		@ManyToMany
		// join columns hace referencia a la columna que hace referencia hacia esta
		// Es decir la tabla usuario_rol va a tener un campo que se llama id_usuario
		// inverseJoinColumns = el inverso, hace referencia a rol
		@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario"),
				inverseJoinColumns = @JoinColumn(name = "rol_id"))
		private Set<Rol> roles = new HashSet<>();

		public Usuario() {
		}

		//Constuctor sin Id ni Roles
		public Usuario(@NotNull String nombre,
					   @NotNull String nombreUsuario,
					   @NotNull String email,
					   @NotNull String password,
					   String topic) {
			this.nombre = nombre;
			this.nombreUsuario = nombreUsuario;
			this.email = email;
			this.password = password;
			this.topic = topic;
		}


	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}

	public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public int getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(int idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getUsuario() {
			return nombreUsuario;
		}

		public void setUsuario(String usuario) {
			this.nombreUsuario = usuario;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Set<Rol> getRoles() {
			return roles;
		}

		public void setRoles(Set<Rol> roles) {
			this.roles = roles;
		}
	}

