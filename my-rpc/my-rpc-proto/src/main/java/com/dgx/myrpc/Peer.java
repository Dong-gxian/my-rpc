package com.dgx.myrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * peer for internet transport
 * @author dgx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    private String host;
    private int port;
}
