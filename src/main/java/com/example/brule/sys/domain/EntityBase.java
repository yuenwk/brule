package com.example.brule.sys.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof final EntityBase that)) {
			return false;
		}
		if (!getClass().equals(object.getClass())) {
			return false;
		}
		_checkIdentity(this);
		_checkIdentity(that);
		return this.id.equals(that.getId());
	}

	/**
	 * Checks the passed entity, if it has an identity. It gets an identity only by
	 * saving.
	 * @param entity the entity to be checked
	 * @throws IllegalStateException the passed entity does not have the identity
	 * attribute set.
	 */
	private void _checkIdentity(final EntityBase entity) {
		if (entity.getId() == null) {
			throw new IllegalStateException("Comparison identity missing in entity: " + entity);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "<" + getId() + ">";
	}

}
