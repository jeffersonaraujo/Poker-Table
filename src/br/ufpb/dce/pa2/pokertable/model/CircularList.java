package br.ufpb.dce.pa2.pokertable.model;

import java.util.ArrayList;
import java.util.Collection;

public class CircularList<E> extends ArrayList<E> {
	private int actualIndex;

	public CircularList() {
		super();
	}
	public CircularList(final Collection<E> collection) {
		super(collection);
	}

	@Override
	public E get(int index) {
		index = index % this.size();
		return super.get(index);
	}
	@Override
	public void add(final int index, final E element) {
		if (index <= this.actualIndex)
			this.actualIndex++;

		super.add(index, element);
	};
	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		if (index <= this.actualIndex)
			this.actualIndex = this.actualIndex + collection.size();

		return super.addAll(index, collection);
	}
	@Override
	public E remove(final int index) {
		final E element = super.remove(index);

		if (index == actualIndex)
			this.actualIndex = 0;
		else if (index < this.actualIndex)
			this.actualIndex--;

		return element;
	}
	@Override
	public boolean remove(final Object object) {
		final int index = this.indexOf(object);

		return this.remove(index) != null;
	}
	@Override
	public boolean removeAll(final Collection<?> collection) {
		int nextIndex;

		if (collection.contains(this.getActual()))
			nextIndex = 0;
		else {
			int discount = 0;
			for (Object object : collection)
				if (this.contains(object)) {
					final int objectIndex = this.indexOf(object);
					if (objectIndex < this.actualIndex)
						discount++;
				}
			nextIndex = this.actualIndex - discount;
		}

		final boolean result = super.removeAll(collection);

		if (result)
			this.actualIndex = nextIndex;

		return result;
	}
	@Override
	public void clear() {
		this.actualIndex = 0;
		super.clear();
	}
	public E previous() {
		if (this.actualIndex == 0)
			this.actualIndex = this.size() - 1;
		else
			this.actualIndex--;

		return super.get(actualIndex);
	}
	public E getActual() {
		return super.get(actualIndex);
	}
	public E next() {
		if (this.actualIndex == this.size() - 1)
			this.actualIndex = 0;
		else
			this.actualIndex++;

		return super.get(actualIndex);
	}
}