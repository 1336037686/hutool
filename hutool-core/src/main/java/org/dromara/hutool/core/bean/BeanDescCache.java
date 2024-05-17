/*
 * Copyright (c) 2023 looly(loolly@aliyun.com)
 * Hutool is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *          https://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package org.dromara.hutool.core.bean;

import org.dromara.hutool.core.func.SerSupplier;
import org.dromara.hutool.core.map.reference.WeakConcurrentMap;

/**
 * Bean属性缓存<br>
 * 缓存用于防止多次反射造成的性能问题
 *
 * @author Looly
 */
public enum BeanDescCache {
	/**
	 * 单例
	 */
	INSTANCE;

	private final WeakConcurrentMap<Class<?>, StrictBeanDesc> bdCache = new WeakConcurrentMap<>();

	/**
	 * 获得属性名和{@link StrictBeanDesc}Map映射
	 *
	 * @param beanClass Bean的类
	 * @param supplier  对象不存在时创建对象的函数
	 * @return 属性名和 {@link StrictBeanDesc}映射
	 * @since 5.4.2
	 */
	public StrictBeanDesc getBeanDesc(final Class<?> beanClass, final SerSupplier<StrictBeanDesc> supplier) {
		return bdCache.computeIfAbsent(beanClass, (key) -> supplier.get());
	}

	/**
	 * 清空全局的Bean属性缓存
	 *
	 * @since 5.7.21
	 */
	public void clear() {
		this.bdCache.clear();
	}
}
