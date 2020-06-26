package com.ashikhman.elastic.document;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Article's author data object.
 */
@Data
@Accessors(chain = true)
public class AuthorDocument {

    /**
     * Author's name.
     */
    private String name;
}
